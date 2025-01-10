package muscaa.chess.board.piece.pieces;

import java.util.Map;

import muscaa.chess.board.Cell;
import muscaa.chess.board.TeamRegistry;
import muscaa.chess.board.TeamValue;
import muscaa.chess.board.matrix.Matrix;
import muscaa.chess.board.piece.AbstractServerPiece;
import muscaa.chess.board.piece.ServerPieceRegistry;
import muscaa.chess.board.piece.PieceUtils;
import muscaa.chess.board.piece.move.AbstractMoveValue;

public class PawnPiece extends AbstractServerPiece {
	
	public PawnPiece(TeamValue team) {
		super(ServerPieceRegistry.PAWN.get(), team);
	}
	
	@Override
	public void findMoves(Map<Cell, AbstractMoveValue> moves, Matrix matrix, Cell from) {
		Cell direction = team == TeamRegistry.WHITE.get() ? new Cell(0, -1) : new Cell(0, 1);
		
		Cell oneStep = from.add(direction);
		PieceUtils.basicPromote(this, moves, matrix, oneStep);
		
		if (totalMoves == 0 && matrix.isInBounds(oneStep) && matrix.get(oneStep) == NullPiece.INSTANCE) {
			Cell twoSteps = from.add(direction.multiply(2));
			PieceUtils.basicPromote(this, moves, matrix, twoSteps);
		}
		
		Cell left = from.add(direction).add(Cell.ONE_ZERO.negate());
		PieceUtils.capturePromote(this, moves, matrix, left);
		
		Cell right = from.add(direction).add(Cell.ONE_ZERO);
		PieceUtils.capturePromote(this, moves, matrix, right);
	}
}
