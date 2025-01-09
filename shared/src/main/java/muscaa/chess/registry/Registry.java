package muscaa.chess.registry;

import java.util.HashMap;
import java.util.Map;

import fluff.functions.gen.obj.VoidFunc1;
import muscaa.chess.utils.NamespacePath;

public class Registry<E extends IRegistryEntry> {
	
	private final Map<NamespacePath, E> reg = new HashMap<>();
	private final NamespacePath id;
	private final VoidFunc1<E> onDispose;
	
	private boolean locked = false;
	
	Registry(NamespacePath id, VoidFunc1<E> onDispose) {
		this.id = id;
		this.onDispose = onDispose;
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
	
	public void dispose() {
		if (onDispose == null) return;
		
        for (Map.Entry<NamespacePath, E> entry : reg.entrySet()) {
            onDispose.invoke(entry.getValue());
        }
	}
	
	public NamespacePath getID() {
		return id;
	}
}
