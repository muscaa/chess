package muscaa.chess.board;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import fluff.network.packet.IPacketOutbound;
import muscaa.chess.board.matrix.Matrix;
import muscaa.chess.board.piece.AbstractPiece;
import muscaa.chess.board.piece.move.AbstractMove;
import muscaa.chess.board.piece.move.moves.CaptureMove;
import muscaa.chess.board.piece.pieces.NullPiece;
import muscaa.chess.network.ChessClientConnection;
import muscaa.chess.network.play.packets.PacketBoard;
import muscaa.chess.network.play.packets.PacketClickCell;
import muscaa.chess.network.play.packets.PacketEndGame;
import muscaa.chess.network.play.packets.PacketHighlightCells;
import muscaa.chess.network.play.packets.PacketStartGame;
import muscaa.chess.network.play.packets.PacketTeam;
import muscaa.chess.registry.registries.HighlightRegistry;
import muscaa.chess.registry.registries.PieceRegistry;
import muscaa.chess.registry.registries.TeamRegistry;

public class ServerBoard {
	
	private final Map<Team, ChessClientConnection> players = new HashMap<>();
	private final Map<ChessClientConnection, Team> teams = new HashMap<>();
	
	private Team turn;
	private Cell selectedCell = Cell.INVALID;
	private List<Cell> inCheckCells = new LinkedList<>();
	private List<Cell> lastMoveCells = new LinkedList<>();
	
	private Matrix matrix;
	
	private Map<Cell, Map<Cell, AbstractMove>> allMoves = new HashMap<>();
	
	public ServerBoard() {
		reset();
	}
	
	public void click(Cell cell) {
		ChessClientConnection player = players.get(turn);
		ChessClientConnection opponent = players.get(TeamRegistry.invert(turn));
		AbstractPiece piece = matrix.get(cell);
		
		if (selectedCell.equals(Cell.INVALID)) {
			if (!piece.equals(NullPiece.INSTANCE) && piece.getTeam() == turn) {
				selectCell(player, null, cell);
			}
			return;
		}
		
		if (!piece.equals(NullPiece.INSTANCE) && piece.getTeam() == turn) {
			selectCell(player, null, selectedCell.equals(cell) ? Cell.INVALID : cell);
			return;
		}
		
		AbstractPiece selectedPiece = matrix.get(selectedCell);
		Map<Cell, AbstractMove> moves = allMoves.get(selectedCell);
		
		AbstractMove move = moves.get(cell);
		if (move == null) {
			selectCell(player, null, Cell.INVALID);
			return;
		}
		
		matrix.begin();
		selectedPiece.onPreMove(matrix, selectedCell, cell);
		move.doMove(matrix, selectedCell, cell);
		selectedPiece.onPostMove(matrix, selectedCell, cell);
		matrix.end();
		
		send(new PacketBoard(matrix));
		findAllMoves();
		
		inCheckCells.clear();
		inCheckCells.addAll(getInCheck(TeamRegistry.invert(turn)));
		
		lastMoveCells.clear();
		lastMoveCells.add(selectedCell);
		lastMoveCells.add(cell);
		
		selectCell(player, opponent, Cell.INVALID);
		
		Map<Cell, Map<Cell, AbstractMove>> remainingMoves = getMoves(TeamRegistry.invert(turn));
		int remainingMovesCount = 0;
		for (Map.Entry<Cell, Map<Cell, AbstractMove>> e : remainingMoves.entrySet()) {
			remainingMovesCount += e.getValue().size();
		}
		
		if (remainingMovesCount == 0) {
			if (!inCheckCells.isEmpty()) {
				endGame(turn);
			} else {
				endGame(TeamRegistry.NULL);
			}
			return;
		}
		
		turn = TeamRegistry.invert(turn);
	}
	
	public void endGame(Team winner) {
		send(new PacketEndGame(winner));
		
		players.get(TeamRegistry.WHITE).disconnect("Game ended!");
		players.get(TeamRegistry.BLACK).disconnect("Game ended!");
	}
	
	public void selectCell(ChessClientConnection player, ChessClientConnection opponent, Cell cell) {
		selectedCell = cell;
		
		List<Highlight> highlights = new LinkedList<>();
		List<Highlight> opponentHighlights = new LinkedList<>();
		
		for (Cell inCheckCell : inCheckCells) {
			Highlight h = new Highlight(inCheckCell, HighlightRegistry.CHECK);
			
			highlights.add(h);
			opponentHighlights.add(h);
		}
		
		for (Cell lastMoveCell : lastMoveCells) {
			Highlight h = new Highlight(lastMoveCell, HighlightRegistry.LAST_MOVE);
			
			highlights.add(h);
			opponentHighlights.add(h);
		}
		
		if (!selectedCell.equals(Cell.INVALID)) {
			highlights.add(new Highlight(selectedCell, HighlightRegistry.SELECTED));
			
			Map<Cell, AbstractMove> moves = allMoves.get(selectedCell);
			for (Map.Entry<Cell, AbstractMove> e : moves.entrySet()) {
				highlights.add(new Highlight(e.getKey(), HighlightRegistry.MOVE_AVAILABLE));
			}
		}
		
		player.send(new PacketHighlightCells(highlights));
		if (opponent != null) {
			opponent.send(new PacketHighlightCells(opponentHighlights));
		}
	}
	
	private void reset() {
		matrix = new Matrix(8, 8);
		
		matrix.begin();
		for (Cell cell : matrix) {
			Team team = cell.y >= matrix.getHeight() / 2 ? TeamRegistry.WHITE : TeamRegistry.BLACK;
			
			if (cell.y == 0 || cell.y == matrix.getHeight() - 1) {
				if (cell.x == 0 || cell.x == matrix.getWidth() - 1) {
					matrix.set(cell, PieceRegistry.ROOK.create(team));
				/*} else if (cell.x == 1 || cell.x == matrix.getWidth() - 2) {
					matrix.set(cell, PieceRegistry.KNIGHT.create(team));
				} else if (cell.x == 2 || cell.x == matrix.getWidth() - 3) {
					matrix.set(cell, PieceRegistry.BISHOP.create(team));
				} else if (cell.x == 3) {
					matrix.set(cell, PieceRegistry.QUEEN.create(team));*/
				} else if (cell.x == matrix.getWidth() - 4) {
					matrix.set(cell, PieceRegistry.KING.create(team));
				} else {
					matrix.set(cell, NullPiece.INSTANCE);
				}
			/*} else if (cell.y == 1 || cell.y == matrix.getHeight() - 2) {
				matrix.set(cell, PieceRegistry.PAWN.create(team));*/
			} else {
				matrix.set(cell, NullPiece.INSTANCE);
			}
		}
		matrix.end();
		
		turn = TeamRegistry.WHITE;
		selectedCell = Cell.INVALID;
	}
	
	private void send(IPacketOutbound packet) {
		for (Map.Entry<Team, ChessClientConnection> e : players.entrySet()) {
			e.getValue().send(packet);
		}
	}
	
	private void findAllMoves() {
		allMoves.clear();
		
		for (Cell from : matrix) {
			AbstractPiece piece = matrix.get(from);
			
			Map<Cell, AbstractMove> moves = new HashMap<>();
			piece.findMoves(moves, matrix, from);
			
			Map<Cell, AbstractMove> validMoves = new HashMap<>();
			for (Map.Entry<Cell, AbstractMove> moveEntry : moves.entrySet()) {
				Cell to = moveEntry.getKey();
				AbstractMove move = moveEntry.getValue();
				
				matrix.begin();
				move.doMove(matrix, from, to);
				matrix.end();
				
				boolean inCheck = !getInCheck(piece.getTeam()).isEmpty();
				matrix.undo(1);
				
				if (inCheck) continue;
				
				validMoves.put(to, move);
			}
			
			allMoves.put(from, validMoves);
		}
	}
	
	private List<Cell> getInCheck(Team team) {
		List<Cell> inCheck = new LinkedList<>();
		for (Cell opponentFrom : matrix) {
			AbstractPiece opponentPiece = matrix.get(opponentFrom);
			if (opponentPiece.getTeam() == team) continue;
			
			Map<Cell, AbstractMove> opponentMoves = new HashMap<>();
			opponentPiece.findMoves(opponentMoves, matrix, opponentFrom);
			
			for (Cell checkable : matrix) {
				AbstractPiece checkablePiece = matrix.get(checkable);
				if (!checkablePiece.isCheckable()) continue;
				if (checkablePiece.getTeam() != team) continue;
				
				AbstractMove opponentMove = opponentMoves.get(checkable);
				if (opponentMove instanceof CaptureMove) {
					inCheck.add(checkable);
				}
			}
		}
		return inCheck;
	}
	
	private Map<Cell, Map<Cell, AbstractMove>> getMoves(Team team) {
		Map<Cell, Map<Cell, AbstractMove>> moves = new HashMap<>();
		for (Map.Entry<Cell, Map<Cell, AbstractMove>> e : allMoves.entrySet()) {
			Cell from = e.getKey();
			AbstractPiece piece = matrix.get(from);
			if (piece.getTeam() != team) continue;

			moves.put(from, e.getValue());
		}
		return moves;
	}
	
	public synchronized void onConnect(ChessClientConnection connection) {
		Team team = players.isEmpty() || !players.containsKey(turn) ? turn : TeamRegistry.invert(turn);
		players.put(team, connection);
		teams.put(connection, team);
		
		if (players.size() == 2) {
			send(new PacketStartGame());
			
			players.get(TeamRegistry.WHITE).send(new PacketTeam(TeamRegistry.WHITE));
			players.get(TeamRegistry.BLACK).send(new PacketTeam(TeamRegistry.BLACK));
			
			send(new PacketBoard(matrix));
			findAllMoves();
			inCheckCells.clear();
			lastMoveCells.clear();
		}
	}
	
	public synchronized void onDisconnect(ChessClientConnection connection) {
		Team team = teams.remove(connection);
		players.remove(team);
		
		reset();
	}
	
	public synchronized void onPacketClickCell(ChessClientConnection connection, PacketClickCell packet) {
		if (players.size() != 2) return;
		
		Team clientTeam = teams.get(connection);
		if (clientTeam != turn) return;
		
		Cell cell = packet.getCell();
		if (cell.x < 0 || cell.y < 0 || cell.x >= matrix.getWidth() || cell.y >= matrix.getHeight()) return;
		
		click(cell);
	}
}
