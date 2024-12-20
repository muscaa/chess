package muscaa.chess.registry;

import java.util.HashMap;
import java.util.Map;

import muscaa.chess.utils.NamespacePath;

public class Registry<E extends IRegistryEntry> {
	
	private final Map<NamespacePath, E> reg = new HashMap<>();
	private final NamespacePath id;
	
	private boolean locked = false;
	
	Registry(NamespacePath id) {
		this.id = id;
	}
	
	public Map<NamespacePath, E> getContents() {
		return Map.copyOf(reg);
	}
	
	public boolean contains(NamespacePath id) {
		return reg.containsKey(id);
	}
	
	public E get(NamespacePath id) {
		return reg.get(id);
	}
	
	public <V extends E> V register(V entry) {
		if (locked) throw new RegistryException("Registry locked!");
		
		NamespacePath id = entry.getID();
		if (contains(id)) throw new RegistryException("Entry already exists!");
		
		reg.put(id, entry);
		return entry;
	}
	
	public void lock() {
		locked = true;
	}
	
	public NamespacePath getID() {
		return id;
	}
}
