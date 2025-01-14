package muscaa.chess.registry;

import java.util.HashMap;
import java.util.Map;

import fluff.events.EventStage;
import fluff.functions.gen.obj.Func1;
import fluff.functions.gen.obj.VoidFunc1;
import muscaa.chess.Chess;
import muscaa.chess.events.IRegistryInitEventListener;
import muscaa.chess.utils.NamespacePath;

public class Registry<V extends IRegistryValue> {
	
	private final Map<NamespacePath, RegistryEntry> entries = new HashMap<>();
	private final Map<NamespacePath, RegistryKey<V>> lookup = new HashMap<>();
	private final Map<V, RegistryKey<V>> reverseLookup = new HashMap<>();
	private final NamespacePath id;
	
	RegistryState state = RegistryState.UNLOCKED;
	
	Registry(NamespacePath id) {
		this.id = id;
	}
	
	public void init() {
		if (state != RegistryState.UNLOCKED) throw new RegistryException("Registry already initialized!");
		state = RegistryState.REGISTER;
		
		Chess.EVENTS.call(
				IRegistryInitEventListener.class,
				IRegistryInitEventListener::onRegistryInitEvent,
				new IRegistryInitEventListener.RegistryInitEvent(
						this
						),
				EventStage.PRE
				);
		state = RegistryState.INIT;
		
		for (Map.Entry<NamespacePath, RegistryEntry> e : entries.entrySet()) {
			RegistryEntry<V> entry = e.getValue();
			RegistryKey<V> key = entry.key;
			
			key.id = entry.id;
			key.value = entry.func.invoke(key);
			
			lookup.put(key.id, key);
			reverseLookup.put(key.value, key);
		}
		entries.clear();
		state = RegistryState.LOCKED;
		
		Chess.EVENTS.call(
				IRegistryInitEventListener.class,
				IRegistryInitEventListener::onRegistryInitEvent,
				new IRegistryInitEventListener.RegistryInitEvent(
						this
						),
				EventStage.POST
				);
	}
	
	public boolean contains(NamespacePath id) {
		return lookup.containsKey(id);
	}
	
	public RegistryKey<V> get(NamespacePath id) {
		return lookup.get(id);
	}
	
	public <T extends V> RegistryKey<T> register(NamespacePath id, Func1<T, RegistryKey<T>> func) {
		if (state.isLocked()) throw new RegistryException("Registry locked!");
		if (entries.containsKey(id)) throw new RegistryException("Entry already exists!");
		
		RegistryEntry<T> entry = new RegistryEntry<>(id, func, new RegistryKey<>(this));
		entries.put(id, entry);
		return entry.key;
	}
	
	public void forEach(VoidFunc1<V> func) {
		if (func == null) return;
		
        for (Map.Entry<NamespacePath, RegistryKey<V>> e : lookup.entrySet()) {
            func.invoke(e.getValue().get());
        }
	}
	
	public NamespacePath getID() {
		return id;
	}
	
	@Override
	public String toString() {
		return "Registry[" + id + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Registry reg)) return false;
		
		return id.equals(reg.id);
	}
	
	@Override
	public int hashCode() {
		return id.hashCode();
	}
}
