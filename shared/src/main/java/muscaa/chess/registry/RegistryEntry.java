package muscaa.chess.registry;

import fluff.functions.gen.obj.Func1;
import muscaa.chess.utils.NamespacePath;

public class RegistryEntry<V extends IRegistryValue> {
	
	public NamespacePath id;
	public Func1<V, RegistryKey<V>> func;
	public RegistryKey<V> key;
	
	public RegistryEntry(NamespacePath id, Func1<V, RegistryKey<V>> func, RegistryKey<V> key) {
        this.id = id;
        this.func = func;
        this.key = key;
	}
}
