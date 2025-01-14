package muscaa.chess.registry;

import muscaa.chess.utils.NamespacePath;

public class RegistryKey<V extends IRegistryValue> {
	
	private final Registry<V> registry;
	
	NamespacePath id;
	V value;
	
	RegistryKey(Registry registry) {
		this.registry = registry;
	}
	
	public Registry<V> getRegistry() {
		return registry;
	}
	
	public NamespacePath getID() {
		return id;
	}
	
	public V get() {
		return registry.state.getValue(this);
	}
	
	@Override
	public String toString() {
		return "RegistryKey[" + id + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof RegistryKey key)) return false;
		
		return id.equals(key.id);
	}
	
	@Override
	public int hashCode() {
		return id.hashCode();
	}
}
