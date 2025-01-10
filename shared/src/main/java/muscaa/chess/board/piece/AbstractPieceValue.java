package muscaa.chess.board.piece;

import muscaa.chess.board.TeamValue;
import muscaa.chess.registry.IRegistryValue;
import muscaa.chess.registry.RegistryKey;

public abstract class AbstractPieceValue<V extends AbstractPieceValue<V, P>, P extends IPiece> implements IRegistryValue<V> {
	
	private final RegistryKey<V> key;
	
	public AbstractPieceValue(RegistryKey<V> key) {
		this.key = key;
	}
	
	public abstract P create(TeamValue team);
	
	@Override
	public RegistryKey<V> getKey() {
		return key;
	}
}
