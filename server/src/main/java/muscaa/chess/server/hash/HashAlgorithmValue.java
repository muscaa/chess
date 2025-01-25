package muscaa.chess.server.hash;

import muscaa.chess.registry.IRegistryValue;
import muscaa.chess.registry.RegistryKey;

public abstract class HashAlgorithmValue implements IRegistryValue<HashAlgorithmValue> {
	
	private final RegistryKey<HashAlgorithmValue> key;
	
	public HashAlgorithmValue(RegistryKey<HashAlgorithmValue> key) {
		this.key = key;
	}
	
	public abstract String hash(String input);
	
	public abstract boolean verify(String hash, String input);
	
	@Override
	public RegistryKey<HashAlgorithmValue> getKey() {
		return key;
	}
}
