package muscaa.chess.network;

import muscaa.chess.registry.IRegistryEntry;
import muscaa.chess.utils.NamespacePath;

public class Intent implements IRegistryEntry {
	
	private final NamespacePath id;
	
	public Intent(NamespacePath id) {
		this.id = id;
	}
	
	@Override
	public NamespacePath getID() {
		return id;
	}
}
