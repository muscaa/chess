package muscaa.chess.board.piece;

import java.util.Map;

import muscaa.chess.board.Cell;
import muscaa.chess.board.matrix.Matrix;
import muscaa.chess.board.piece.move.AbstractMove;
import muscaa.chess.board.piece.pieces.NullPiece;
import muscaa.chess.registry.registries.MoveRegistry;
import muscaa.chess.registry.registries.TeamRegistry;

public class PieceUtils {
	
	public static boolean basicCapture(AbstractPiece piece, Map<Cell, AbstractMove> moves, Matrix matrix, Cell to) {
		if (!matrix.isInBounds(to)) return false;
		
		AbstractPiece toPiece = matrix.get(to);
		
		if (toPiece.getTeam() == piece.getTeam()) return false;
		if (toPiece.getTeam() == TeamRegistry.invert(piece.getTeam())) {
			moves.put(to, MoveRegistry.CAPTURE);
			return false;
		}
		
		moves.put(to, MoveRegistry.BASIC);
		return true;
	}
	
	public static void basicPromote(AbstractPiece piece, Map<Cell, AbstractMove> moves, Matrix matrix, Cell to) {
		if (!matrix.isInBounds(to)) return;
		
		AbstractPiece toPiece = matrix.get(to);
		if (toPiece != NullPiece.INSTANCE) return;
		
		if (canPromote(piece, to)) {
			moves.put(to, MoveRegistry.PROMOTE);
		} else {
			moves.put(to, MoveRegistry.BASIC);
		}
	}
	
	public static void capturePromote(AbstractPiece piece, Map<Cell, AbstractMove> moves, Matrix matrix, Cell to) {
		if (!matrix.isInBounds(to)) return;
		
		AbstractPiece toPiece = matrix.get(to);
		if (toPiece == NullPiece.INSTANCE || toPiece.getTeam() == piece.getTeam()) return;
		
		if (canPromote(piece, to)) {
			moves.put(to, MoveRegistry.PROMOTE);
		} else {
			moves.put(to, MoveRegistry.BASIC);
		}
	}
	
	public static boolean canPromote(AbstractPiece piece, Cell to) {
		return (piece.getTeam() == TeamRegistry.WHITE && to.y == 0) ||
				(piece.getTeam() == TeamRegistry.BLACK && to.y == 7);
	}
}
