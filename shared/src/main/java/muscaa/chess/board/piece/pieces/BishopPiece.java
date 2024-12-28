package muscaa.chess.board.piece.pieces;

import java.util.Map;

import muscaa.chess.board.Cell;
import muscaa.chess.board.Team;
import muscaa.chess.board.matrix.Matrix;
import muscaa.chess.board.piece.AbstractPiece;
import muscaa.chess.board.piece.PieceEntry;
import muscaa.chess.board.piece.PieceUtils;
import muscaa.chess.board.piece.move.AbstractMove;

public class BishopPiece extends AbstractPiece {
	
	public BishopPiece(PieceEntry<BishopPiece> regEntry, Team team) {
		super(regEntry, team);
	}
	
	@Override
	public void findMoves(Map<Cell, AbstractMove> moves, Matrix matrix, Cell from) {
		// top left
		for (Cell cell = from.subtract(Cell.ONE_ONE);
				matrix.isInBounds(cell);
				cell = cell.subtract(Cell.ONE_ONE)) {
			if (!PieceUtils.basicCapture(this, moves, matrix, cell)) break;
		}
		
		// bottom right
		for (Cell cell = from.add(Cell.ONE_ONE);
				matrix.isInBounds(cell);
				cell = cell.add(Cell.ONE_ONE)) {
			if (!PieceUtils.basicCapture(this, moves, matrix, cell)) break;
		}
		
		// top right
		for (Cell cell = from.add(Cell.ONE_ZERO).subtract(Cell.ZERO_ONE);
				matrix.isInBounds(cell);
				cell = cell.add(Cell.ONE_ZERO).subtract(Cell.ZERO_ONE)) {
			if (!PieceUtils.basicCapture(this, moves, matrix, cell)) break;
		}
		
		// bottom left
		for (Cell cell = from.subtract(Cell.ONE_ZERO).add(Cell.ZERO_ONE);
				matrix.isInBounds(cell);
				cell = cell.subtract(Cell.ONE_ZERO).add(Cell.ZERO_ONE)) {
			if (!PieceUtils.basicCapture(this, moves, matrix, cell)) break;
		}
	}
}
