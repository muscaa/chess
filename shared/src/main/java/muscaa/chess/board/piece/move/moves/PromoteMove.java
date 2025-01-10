package muscaa.chess.board.piece.move.moves;

import muscaa.chess.board.Cell;
import muscaa.chess.board.matrix.Matrix;
import muscaa.chess.board.piece.AbstractServerPiece;
import muscaa.chess.board.piece.ServerPieceRegistry;
import muscaa.chess.board.piece.move.AbstractMoveValue;
import muscaa.chess.board.piece.pieces.NullPiece;
import muscaa.chess.registry.RegistryKey;

public class PromoteMove extends CaptureMove {
	
	public PromoteMove(RegistryKey<AbstractMoveValue> key) {
		super(key);
	}
	
	@Override
	public void doMove(Matrix matrix, Cell from, Cell to) {
		AbstractServerPiece fromPiece = matrix.get(from);
		
		matrix.set(to, ServerPieceRegistry.QUEEN.get().create(fromPiece.getTeam()));
		matrix.set(from, NullPiece.INSTANCE);
	}
}
