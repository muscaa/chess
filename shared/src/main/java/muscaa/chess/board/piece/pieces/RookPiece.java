package muscaa.chess.board.piece.pieces;

import java.util.Map;

import muscaa.chess.board.Cell;
import muscaa.chess.board.TeamValue;
import muscaa.chess.board.matrix.ServerMatrix;
import muscaa.chess.board.piece.AbstractServerPiece;
import muscaa.chess.board.piece.ServerPieceRegistry;
import muscaa.chess.board.piece.PieceUtils;
import muscaa.chess.board.piece.move.AbstractMoveValue;

public class RookPiece extends AbstractServerPiece {
	
	public RookPiece(TeamValue team) {
		super(ServerPieceRegistry.ROOK.get(), team);
	}
	
	@Override
	public void findMoves(Map<Cell, AbstractMoveValue> moves, ServerMatrix matrix, Cell from) {
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
