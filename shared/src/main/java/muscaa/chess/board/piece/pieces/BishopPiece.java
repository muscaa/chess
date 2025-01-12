package muscaa.chess.board.piece.pieces;

import java.util.Map;

import muscaa.chess.board.Cell;
import muscaa.chess.board.TeamValue;
import muscaa.chess.board.matrix.ServerMatrix;
import muscaa.chess.board.piece.AbstractServerPiece;
import muscaa.chess.board.piece.ServerPieceRegistry;
import muscaa.chess.board.piece.PieceUtils;
import muscaa.chess.board.piece.move.AbstractMoveValue;

public class BishopPiece extends AbstractServerPiece {
	
	public BishopPiece(TeamValue team) {
		super(ServerPieceRegistry.BISHOP.get(), team);
	}
	
	@Override
	public void findMoves(Map<Cell, AbstractMoveValue> moves, ServerMatrix matrix, Cell from) {
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
