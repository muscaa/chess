package muscaa.chess.server.hash.algorithms;

import java.nio.charset.StandardCharsets;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import de.mkammerer.argon2.Argon2Factory.Argon2Types;
import muscaa.chess.registry.RegistryKey;
import muscaa.chess.server.hash.HashAlgorithmValue;

public class Argon2HashAlgorithmValue extends HashAlgorithmValue {
	
	private static final int ITERATIONS = 30;
	private static final int MEMORY = 65536;
	private static final int PARALLELISM = 1;
	
	private final Argon2 argon2 = Argon2Factory.create(Argon2Types.ARGON2id);
	
	public Argon2HashAlgorithmValue(RegistryKey<HashAlgorithmValue> key) {
		super(key);
	}
	
	@Override
	public String hash(String input) {
	    return argon2.hash(ITERATIONS, MEMORY, PARALLELISM, input.getBytes(StandardCharsets.UTF_8));
	}
	
	@Override
	public boolean verify(String hash, String input) {
		return argon2.verify(hash, input.getBytes(StandardCharsets.UTF_8));
	}
}
