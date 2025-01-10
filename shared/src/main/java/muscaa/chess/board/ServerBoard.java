package muscaa.chess.board;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import fluff.network.packet.IPacketOutbound;
import muscaa.chess.board.matrix.Matrix;
import muscaa.chess.board.piece.AbstractServerPiece;
import muscaa.chess.board.piece.ServerPieceRegistry;
import muscaa.chess.board.piece.move.AbstractMoveValue;
import muscaa.chess.board.piece.move.moves.CaptureMove;
import muscaa.chess.board.piece.pieces.NullPiece;
import muscaa.chess.network.ChessClientConnection;
import muscaa.chess.network.play.packets.PacketBoard;
import muscaa.chess.network.play.packets.PacketClickCell;
import muscaa.chess.network.play.packets.PacketGameEnd;
import muscaa.chess.network.play.packets.PacketHighlightCells;
import muscaa.chess.network.play.packets.PacketGameStart;
import muscaa.chess.network.play.packets.PacketTeam;

public class ServerBoard {
	
	private final Map<TeamValue, ChessClientConnection> players = new HashMap<>();
	private final Map<ChessClientConnection, TeamValue> teams = new HashMap<>();
	
	private TeamValue turn;
	private Cell selectedCell = Cell.INVALID;
	private List<Cell> inCheckCells = new LinkedList<>();
	private List<Cell> lastMoveCells = new LinkedList<>();
	
	private Matrix matrix;
	
	private Map<Cell, Map<Cell, AbstractMoveValue>> allMoves = new HashMap<>();
	
	public ServerBoard() {
		reset();
	}
	
	public void click(Cell cell) {
		ChessClientConnection player = players.get(turn);
		ChessClientConnection opponent = players.get(turn.invert());
		AbstractServerPiece piece = matrix.get(cell);
		
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
		
		AbstractServerPiece selectedPiece = matrix.get(selectedCell);
		Map<Cell, AbstractMoveValue> moves = allMoves.get(selectedCell);
		
		AbstractMoveValue move = moves.get(cell);
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
		inCheckCells.addAll(getInCheck(turn.invert()));
		
		lastMoveCells.clear();
		lastMoveCells.add(selectedCell);
		lastMoveCells.add(cell);
		
		selectCell(player, opponent, Cell.INVALID);
		
		Map<Cell, Map<Cell, AbstractMoveValue>> remainingMoves = getMoves(turn.invert());
		int remainingMovesCount = 0;
		for (Map.Entry<Cell, Map<Cell, AbstractMoveValue>> e : remainingMoves.entrySet()) {
			remainingMovesCount += e.getValue().size();
		}
		
		if (remainingMovesCount == 0) {
			if (!inCheckCells.isEmpty()) {
				endGame(turn);
			} else {
				endGame(TeamRegistry.NULL.get());
			}
			return;
		}
		
		turn = turn.invert();
	}
	
	public void endGame(TeamValue winner) {
		send(new PacketGameEnd(winner));
		
		players.get(TeamRegistry.WHITE.get()).disconnect("Game ended!");
		players.get(TeamRegistry.BLACK.get()).disconnect("Game ended!");
	}
	
	public void selectCell(ChessClientConnection player, ChessClientConnection opponent, Cell cell) {
		selectedCell = cell;
		
		List<Highlight> highlights = new LinkedList<>();
		List<Highlight> opponentHighlights = new LinkedList<>();
		
		for (Cell inCheckCell : inCheckCells) {
			Highlight h = new Highlight(inCheckCell, HighlightRegistry.CHECK.get());
			
			highlights.add(h);
			opponentHighlights.add(h);
		}
		
		for (Cell lastMoveCell : lastMoveCells) {
			Highlight h = new Highlight(lastMoveCell, HighlightRegistry.LAST_MOVE.get());
			
			highlights.add(h);
			opponentHighlights.add(h);
		}
		
		if (!selectedCell.equals(Cell.INVALID)) {
			highlights.add(new Highlight(selectedCell, HighlightRegistry.SELECTED.get()));
			
			Map<Cell, AbstractMoveValue> moves = allMoves.get(selectedCell);
			for (Map.Entry<Cell, AbstractMoveValue> e : moves.entrySet()) {
				highlights.add(new Highlight(e.getKey(), HighlightRegistry.MOVE_AVAILABLE.get()));
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
			TeamValue team = cell.y >= matrix.getHeight() / 2 ? TeamRegistry.WHITE.get() : TeamRegistry.BLACK.get();
			
			if (cell.y == 0 || cell.y == matrix.getHeight() - 1) {
				if (cell.x == 0 || cell.x == matrix.getWidth() - 1) {
					matrix.set(cell, ServerPieceRegistry.ROOK.get().create(team));
				} else if (cell.x == 1 || cell.x == matrix.getWidth() - 2) {
					matrix.set(cell, ServerPieceRegistry.KNIGHT.get().create(team));
				} else if (cell.x == 2 || cell.x == matrix.getWidth() - 3) {
					matrix.set(cell, ServerPieceRegistry.BISHOP.get().create(team));
				} else if (cell.x == 3) {
					matrix.set(cell, ServerPieceRegistry.QUEEN.get().create(team));
				} else if (cell.x == matrix.getWidth() - 4) {
					matrix.set(cell, ServerPieceRegistry.KING.get().create(team));
				} else {
					matrix.set(cell, NullPiece.INSTANCE);
				}
			} else if (cell.y == 1 || cell.y == matrix.getHeight() - 2) {
				matrix.set(cell, ServerPieceRegistry.PAWN.get().create(team));
			} else {
				matrix.set(cell, NullPiece.INSTANCE);
			}
		}
		matrix.end();
		
		turn = TeamRegistry.WHITE.get();
		selectedCell = Cell.INVALID;
	}
	
	private void send(IPacketOutbound packet) {
		for (Map.Entry<TeamValue, ChessClientConnection> e : players.entrySet()) {
			e.getValue().send(packet);
		}
	}
	
	private void findAllMoves() {
		allMoves.clear();
		
		for (Cell from : matrix) {
			AbstractServerPiece piece = matrix.get(from);
			
			Map<Cell, AbstractMoveValue> moves = new HashMap<>();
			piece.findMoves(moves, matrix, from);
			
			Map<Cell, AbstractMoveValue> validMoves = new HashMap<>();
			for (Map.Entry<Cell, AbstractMoveValue> moveEntry : moves.entrySet()) {
				Cell to = moveEntry.getKey();
				AbstractMoveValue move = moveEntry.getValue();
				
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
	
	private List<Cell> getInCheck(TeamValue team) {
		List<Cell> inCheck = new LinkedList<>();
		for (Cell opponentFrom : matrix) {
			AbstractServerPiece opponentPiece = matrix.get(opponentFrom);
			if (opponentPiece.getTeam() == team) continue;
			
			Map<Cell, AbstractMoveValue> opponentMoves = new HashMap<>();
			opponentPiece.findMoves(opponentMoves, matrix, opponentFrom);
			
			for (Cell checkable : matrix) {
				AbstractServerPiece checkablePiece = matrix.get(checkable);
				if (!checkablePiece.isCheckable()) continue;
				if (checkablePiece.getTeam() != team) continue;
				
				AbstractMoveValue opponentMove = opponentMoves.get(checkable);
				if (opponentMove instanceof CaptureMove) {
					inCheck.add(checkable);
				}
			}
		}
		return inCheck;
	}
	
	private Map<Cell, Map<Cell, AbstractMoveValue>> getMoves(TeamValue team) {
		Map<Cell, Map<Cell, AbstractMoveValue>> moves = new HashMap<>();
		for (Map.Entry<Cell, Map<Cell, AbstractMoveValue>> e : allMoves.entrySet()) {
			Cell from = e.getKey();
			AbstractServerPiece piece = matrix.get(from);
			if (piece.getTeam() != team) continue;
			
			moves.put(from, e.getValue());
		}
		return moves;
	}
	
	public synchronized void onConnect(ChessClientConnection connection) {
		TeamValue team = players.isEmpty() || !players.containsKey(turn) ? turn : turn.invert();
		players.put(team, connection);
		teams.put(connection, team);
		
		if (players.size() == 2) {
			send(new PacketGameStart());
			
			players.get(TeamRegistry.WHITE.get()).send(new PacketTeam(TeamRegistry.WHITE.get()));
			players.get(TeamRegistry.BLACK.get()).send(new PacketTeam(TeamRegistry.BLACK.get()));
			
			send(new PacketBoard(matrix));
			findAllMoves();
			inCheckCells.clear();
			lastMoveCells.clear();
		}
	}
	
	public synchronized void onDisconnect(ChessClientConnection connection) {
		TeamValue team = teams.remove(connection);
		players.remove(team);
		
		reset();
	}
	
	public synchronized void onPacketClickCell(ChessClientConnection connection, PacketClickCell packet) {
		if (players.size() != 2) return;
		
		TeamValue clientTeam = teams.get(connection);
		if (clientTeam != turn) return;
		
		Cell cell = packet.getCell();
		if (cell.x < 0 || cell.y < 0 || cell.x >= matrix.getWidth() || cell.y >= matrix.getHeight()) return;
		
		click(cell);
	}
}
