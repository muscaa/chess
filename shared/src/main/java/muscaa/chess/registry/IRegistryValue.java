package muscaa.chess.registry;

public interface IRegistryValue<V extends IRegistryValue<V>> {
	
	RegistryKey<V> getKey();
}
