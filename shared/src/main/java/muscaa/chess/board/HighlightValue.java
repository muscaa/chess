package muscaa.chess.board;

import muscaa.chess.registry.IRegistryValue;
import muscaa.chess.registry.RegistryKey;

public class HighlightValue implements IRegistryValue<HighlightValue> {
	
	private final RegistryKey<HighlightValue> key;
	
	public HighlightValue(RegistryKey<HighlightValue> key) {
		this.key = key;
	}
	
	@Override
	public RegistryKey<HighlightValue> getKey() {
		return key;
	}
}
