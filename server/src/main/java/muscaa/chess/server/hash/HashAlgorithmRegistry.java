package muscaa.chess.server.hash;

import muscaa.chess.Chess;
import muscaa.chess.registry.Registries;
import muscaa.chess.registry.Registry;
import muscaa.chess.registry.RegistryKey;
import muscaa.chess.server.hash.algorithms.Argon2HashAlgorithmValue;

public class HashAlgorithmRegistry {
	
	public static final Registry<HashAlgorithmValue> REG = Registries.create(Chess.NAMESPACE.path("hash_algorithms"));
	
	public static final RegistryKey<HashAlgorithmValue> ARGON2 = REG.register(Chess.NAMESPACE.path("argon2"), Argon2HashAlgorithmValue::new);
	
	public static void init() {
		REG.init();
	}
}
