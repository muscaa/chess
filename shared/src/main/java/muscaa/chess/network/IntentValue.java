package muscaa.chess.network;

import muscaa.chess.registry.IRegistryValue;
import muscaa.chess.registry.RegistryKey;

public class IntentValue implements IRegistryValue<IntentValue> {
	
	private final RegistryKey<IntentValue> key;
	
	public IntentValue(RegistryKey<IntentValue> key) {
		this.key = key;
	}
	
	@Override
	public RegistryKey<IntentValue> getKey() {
		return key;
	}
}
