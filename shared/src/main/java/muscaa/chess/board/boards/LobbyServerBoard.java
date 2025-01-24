package muscaa.chess.board.boards;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import muscaa.chess.board.AbstractServerBoard;
import muscaa.chess.board.Cell;
import muscaa.chess.board.Highlight;
import muscaa.chess.board.HighlightRegistry;
import muscaa.chess.board.TeamRegistry;
import muscaa.chess.board.TeamValue;
import muscaa.chess.board.matrix.ServerMatrix;
import muscaa.chess.board.piece.AbstractServerPiece;
import muscaa.chess.board.piece.ServerPieceRegistry;
import muscaa.chess.board.piece.move.AbstractMoveValue;
import muscaa.chess.board.piece.move.MoveUtils;
import muscaa.chess.board.piece.pieces.NullPiece;
import muscaa.chess.player.AbstractServerPlayer;

public class LobbyServerBoard extends AbstractServerBoard {
	
	public Cell selectedCell;
	public List<Cell> lastMoveCells;
	public List<Cell> inCheckCells;
	
	@Override
	public void startGame() {
		reset();
		MoveUtils.findAllMoves(matrix, allMoves);
		
		updateBoard();
		
		super.startGame();
	}
	
	@Override
	public void onClick(Cell cell) {
		synchronized (players) {
			AbstractServerPlayer player = teams.get(turn);
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
			
			Map<Cell, AbstractMoveValue> moves = allMoves.get(selectedCell);
			
			AbstractMoveValue move = moves.get(cell);
			if (move == null) {
				selectCell(player, null, Cell.INVALID);
				return;
			}
			
			doMove(move, selectedCell, cell);
		}
	}
	
	@Override
	public void doMove(AbstractMoveValue move, Cell from, Cell to) {
		AbstractServerPiece selectedPiece = matrix.get(from);
		AbstractServerPlayer player = teams.get(turn);
		AbstractServerPlayer opponent = teams.get(turn.invert());
		
		matrix.begin();
		selectedPiece.onPreMove(matrix, from, to);
		move.doMove(matrix, from, to);
		selectedPiece.onPostMove(matrix, from, to);
		matrix.end();
		
		updateBoard();
		MoveUtils.findAllMoves(matrix, allMoves);
		
		inCheckCells.clear();
		inCheckCells.addAll(MoveUtils.getInCheck(matrix, turn.invert()));
		
		lastMoveCells.clear();
		lastMoveCells.add(from);
		lastMoveCells.add(to);
		
		selectCell(player, opponent, Cell.INVALID);
		
		Map<Cell, Map<Cell, AbstractMoveValue>> remainingMoves = MoveUtils.getMoves(matrix, allMoves, turn.invert());
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
		
		setTurn(turn.invert());
	}
	
	public void reset() {
		matrix = new ServerMatrix(8, 8);
		
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
		
		setTurn(TeamRegistry.WHITE.get());
		selectedCell = Cell.INVALID;
		inCheckCells = new LinkedList<>();
		lastMoveCells = new LinkedList<>();
		allMoves = new HashMap<>();
	}
	
	public void selectCell(AbstractServerPlayer player, AbstractServerPlayer opponent, Cell cell) {
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
		
		player.setHighlights(highlights);
		if (opponent != null) {
			opponent.setHighlights(opponentHighlights);
		}
	}
}
