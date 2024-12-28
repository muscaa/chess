package muscaa.chess.board.piece.pieces;

import java.util.Map;

import muscaa.chess.board.Cell;
import muscaa.chess.board.Team;
import muscaa.chess.board.matrix.Matrix;
import muscaa.chess.board.piece.AbstractPiece;
import muscaa.chess.board.piece.PieceEntry;
import muscaa.chess.board.piece.PieceUtils;
import muscaa.chess.board.piece.move.AbstractMove;

public class RookPiece extends AbstractPiece {
	
	public RookPiece(PieceEntry<RookPiece> regEntry, Team team) {
		super(regEntry, team);
	}
	
	@Override
	public void findMoves(Map<Cell, AbstractMove> moves, Matrix matrix, Cell from) {
		// horizontal left
		for (Cell cell = from.subtract(Cell.ONE_ZERO);
				matrix.isInBounds(cell);
				cell = cell.subtract(Cell.ONE_ZERO)) {
			if (!PieceUtils.basicCapture(this, moves, matrix, cell)) break;
		}
		
		// horizontal right
		for (Cell cell = from.add(Cell.ONE_ZERO);
				matrix.isInBounds(cell);
				cell = cell.add(Cell.ONE_ZERO)) {
			if (!PieceUtils.basicCapture(this, moves, matrix, cell)) break;
		}
		
		// horizontal up
		for (Cell cell = from.subtract(Cell.ZERO_ONE);
				matrix.isInBounds(cell);
				cell = cell.subtract(Cell.ZERO_ONE)) {
			if (!PieceUtils.basicCapture(this, moves, matrix, cell)) break;
		}
		
		// horizontal down
		for (Cell cell = from.add(Cell.ZERO_ONE);
				matrix.isInBounds(cell);
				cell = cell.add(Cell.ZERO_ONE)) {
			if (!PieceUtils.basicCapture(this, moves, matrix, cell)) break;
		}
	}
}
