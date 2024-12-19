package muscaa.chess.shared.board.piece.pieces;

import java.util.Map;

import muscaa.chess.shared.board.Cell;
import muscaa.chess.shared.board.Team;
import muscaa.chess.shared.board.matrix.Matrix;
import muscaa.chess.shared.board.piece.AbstractPiece;
import muscaa.chess.shared.board.piece.PieceEntry;
import muscaa.chess.shared.board.piece.PieceUtils;
import muscaa.chess.shared.board.piece.move.AbstractMove;

public class RookPiece extends AbstractPiece {
	
	public RookPiece(PieceEntry<RookPiece> regEntry, Team team) {
		super(regEntry, team);
	}
	
	@Override
	public void findMoves(Map<Cell, AbstractMove> moves, Matrix matrix, Cell from) {
		// horizontal left
		for (Cell cell = from.copy().subtract(Cell.ONE_ZERO);
				matrix.isInBounds(cell);
				cell.subtract(Cell.ONE_ZERO)) {
			if (!PieceUtils.basicCapture(this, moves, matrix, cell)) break;
		}
		
		// horizontal right
		for (Cell cell = from.copy().add(Cell.ONE_ZERO);
				matrix.isInBounds(cell);
				cell.add(Cell.ONE_ZERO)) {
			if (!PieceUtils.basicCapture(this, moves, matrix, cell)) break;
		}
		
		// horizontal up
		for (Cell cell = from.copy().subtract(Cell.ZERO_ONE);
				matrix.isInBounds(cell);
				cell.subtract(Cell.ZERO_ONE)) {
			if (!PieceUtils.basicCapture(this, moves, matrix, cell)) break;
		}
		
		// horizontal down
		for (Cell cell = from.copy().add(Cell.ZERO_ONE);
				matrix.isInBounds(cell);
				cell.add(Cell.ZERO_ONE)) {
			if (!PieceUtils.basicCapture(this, moves, matrix, cell)) break;
		}
	}
}
