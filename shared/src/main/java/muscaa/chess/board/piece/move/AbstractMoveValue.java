package muscaa.chess.board.piece.move;

import muscaa.chess.board.Cell;
import muscaa.chess.board.matrix.ServerMatrix;
import muscaa.chess.registry.IRegistryValue;
import muscaa.chess.registry.RegistryKey;

public abstract class AbstractMoveValue implements IRegistryValue<AbstractMoveValue> {
	
	private final RegistryKey<AbstractMoveValue> key;
	
	public AbstractMoveValue(RegistryKey<AbstractMoveValue> key) {
		this.key = key;
	}
	
	public abstract void doMove(ServerMatrix matrix, Cell from, Cell to);
	
	@Override
	public RegistryKey<AbstractMoveValue> getKey() {
		return key;
	}
}
