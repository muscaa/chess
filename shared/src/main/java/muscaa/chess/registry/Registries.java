package muscaa.chess.registry;

import java.util.HashMap;
import java.util.Map;

import fluff.functions.gen.obj.VoidFunc1;
import muscaa.chess.utils.NamespacePath;

public class Registries {
	
	private static final Map<NamespacePath, Registry<?>> REGISTRIES = new HashMap<>();
	
	public static boolean exists(NamespacePath id) {
		return REGISTRIES.containsKey(id);
	}
	
	public static <E extends IRegistryEntry> Registry<E> get(NamespacePath id) {
		return exists(id) ? (Registry<E>) REGISTRIES.get(id) : null;
	}
	
	public static <E extends IRegistryEntry> Registry<E> create(NamespacePath id) {
		return create(id, null);
	}
	
	public static <E extends IRegistryEntry> Registry<E> create(NamespacePath id, VoidFunc1<E> onDispose) {
		if (exists(id)) throw new RegistryException("Registry already exists!");
		
		Registry<E> reg = new Registry<>(id, onDispose);
		REGISTRIES.put(id, reg);
		return reg;
	}
}
