package muscaa.chess.board.piece;

import java.util.Map;

import muscaa.chess.board.Cell;
import muscaa.chess.board.TeamRegistry;
import muscaa.chess.board.matrix.Matrix;
import muscaa.chess.board.piece.move.AbstractMoveValue;
import muscaa.chess.board.piece.move.MoveRegistry;
import muscaa.chess.board.piece.pieces.NullPiece;

public class PieceUtils {
	
	public static boolean basicCapture(AbstractServerPiece piece, Map<Cell, AbstractMoveValue> moves, Matrix matrix, Cell to) {
		if (!matrix.isInBounds(to)) return false;
		
		AbstractServerPiece toPiece = matrix.get(to);
		
		if (toPiece.getTeam() == piece.getTeam()) return false;
		if (toPiece.getTeam() == piece.getTeam().invert()) {
			moves.put(to, MoveRegistry.CAPTURE.get());
			return false;
		}
		
		moves.put(to, MoveRegistry.BASIC.get());
		return true;
	}
	
	public static void basicPromote(AbstractServerPiece piece, Map<Cell, AbstractMoveValue> moves, Matrix matrix, Cell to) {
		if (!matrix.isInBounds(to)) return;
		
		AbstractServerPiece toPiece = matrix.get(to);
		if (toPiece != NullPiece.INSTANCE) return;
		
		if (canPromote(piece, to)) {
			moves.put(to, MoveRegistry.PROMOTE.get());
		} else {
			moves.put(to, MoveRegistry.BASIC.get());
		}
	}
	
	public static void capturePromote(AbstractServerPiece piece, Map<Cell, AbstractMoveValue> moves, Matrix matrix, Cell to) {
		if (!matrix.isInBounds(to)) return;
		
		AbstractServerPiece toPiece = matrix.get(to);
		if (toPiece == NullPiece.INSTANCE || toPiece.getTeam() == piece.getTeam()) return;
		
		if (canPromote(piece, to)) {
			moves.put(to, MoveRegistry.PROMOTE.get());
		} else {
			moves.put(to, MoveRegistry.CAPTURE.get());
		}
	}
	
	public static boolean canPromote(AbstractServerPiece piece, Cell to) {
		return (piece.getTeam() == TeamRegistry.WHITE.get() && to.y == 0) ||
				(piece.getTeam() == TeamRegistry.BLACK.get() && to.y == 7);
	}
}
