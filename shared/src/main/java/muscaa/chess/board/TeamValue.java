package muscaa.chess.board;

import muscaa.chess.registry.IRegistryValue;
import muscaa.chess.registry.RegistryKey;

public class TeamValue implements IRegistryValue<TeamValue> {
	
	private final RegistryKey<TeamValue> key;
	
	public TeamValue(RegistryKey<TeamValue> key) {
		this.key = key;
	}
	
	public TeamValue invert() {
		if (this == TeamRegistry.WHITE.get()) return TeamRegistry.BLACK.get();
		if (this == TeamRegistry.BLACK.get()) return TeamRegistry.WHITE.get();
		return TeamRegistry.NULL.get();
	}
	
	@Override
	public RegistryKey<TeamValue> getKey() {
		return key;
	}
}
