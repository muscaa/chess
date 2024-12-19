package muscaa.chess.shared.board.piece.pieces;

import java.util.Map;

import muscaa.chess.shared.board.Cell;
import muscaa.chess.shared.board.Team;
import muscaa.chess.shared.board.matrix.Matrix;
import muscaa.chess.shared.board.piece.AbstractPiece;
import muscaa.chess.shared.board.piece.PieceEntry;
import muscaa.chess.shared.board.piece.PieceUtils;
import muscaa.chess.shared.board.piece.move.AbstractMove;

public class BishopPiece extends AbstractPiece {
	
	public BishopPiece(PieceEntry<BishopPiece> regEntry, Team team) {
		super(regEntry, team);
	}
	
	@Override
	public void findMoves(Map<Cell, AbstractMove> moves, Matrix matrix, Cell from) {
		// top left
		for (Cell cell = from.copy().subtract(Cell.ONE_ONE);
				matrix.isInBounds(cell);
				cell.subtract(Cell.ONE_ONE)) {
			if (!PieceUtils.basicCapture(this, moves, matrix, cell)) break;
		}
		
		// bottom right
		for (Cell cell = from.copy().add(Cell.ONE_ONE);
				matrix.isInBounds(cell);
				cell.add(Cell.ONE_ONE)) {
			if (!PieceUtils.basicCapture(this, moves, matrix, cell)) break;
		}
		
		// top right
		for (Cell cell = from.copy().add(Cell.ONE_ZERO).subtract(Cell.ZERO_ONE);
				matrix.isInBounds(cell);
				cell.add(Cell.ONE_ZERO).subtract(Cell.ZERO_ONE)) {
			if (!PieceUtils.basicCapture(this, moves, matrix, cell)) break;
		}
		
		// bottom left
		for (Cell cell = from.copy().subtract(Cell.ONE_ZERO).add(Cell.ZERO_ONE);
				matrix.isInBounds(cell);
				cell.subtract(Cell.ONE_ZERO).add(Cell.ZERO_ONE)) {
			if (!PieceUtils.basicCapture(this, moves, matrix, cell)) break;
		}
	}
}
