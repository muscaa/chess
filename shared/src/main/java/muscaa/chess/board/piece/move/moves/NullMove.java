package muscaa.chess.board.piece.move.moves;

import muscaa.chess.board.Cell;
import muscaa.chess.board.matrix.ServerMatrix;
import muscaa.chess.board.piece.move.AbstractMoveValue;
import muscaa.chess.registry.RegistryKey;

public class NullMove extends AbstractMoveValue {
	
	public NullMove(RegistryKey<AbstractMoveValue> key) {
		super(key);
	}
	
	@Override
	public void doMove(ServerMatrix matrix, Cell from, Cell to) {}
}
