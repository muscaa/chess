package muscaa.chess.registry;

import java.util.HashMap;
import java.util.Map;

import muscaa.chess.utils.NamespacePath;

public class Registries {
	
	private static final Map<NamespacePath, Registry<?>> REGISTRIES = new HashMap<>();
	
	public static boolean exists(NamespacePath id) {
		return REGISTRIES.containsKey(id);
	}
	
	public static <V extends IRegistryValue> Registry<V> get(NamespacePath id) {
		return exists(id) ? (Registry<V>) REGISTRIES.get(id) : null;
	}
	
	public static <V extends IRegistryValue> Registry<V> create(NamespacePath id) {
		if (exists(id)) throw new RegistryException("Registry already exists!");
		
		Registry<V> reg = new Registry<>(id);
		REGISTRIES.put(id, reg);
		return reg;
	}
}
