package muscaa.chess.board.piece.move.moves;

import muscaa.chess.board.Cell;
import muscaa.chess.board.matrix.ServerMatrix;
import muscaa.chess.board.piece.AbstractServerPiece;
import muscaa.chess.board.piece.move.AbstractMoveValue;
import muscaa.chess.board.piece.pieces.NullPiece;
import muscaa.chess.registry.RegistryKey;

public class BasicMove extends AbstractMoveValue {
	
	public BasicMove(RegistryKey<AbstractMoveValue> key) {
		super(key);
	}
	
	@Override
	public void doMove(ServerMatrix matrix, Cell from, Cell to) {
		AbstractServerPiece fromPiece = matrix.get(from);
		
		matrix.set(to, fromPiece);
		matrix.set(from, NullPiece.INSTANCE);
	}
}
