package muscaa.chess.board;

import muscaa.chess.registry.IRegistryEntry;
import muscaa.chess.utils.NamespacePath;

public class Team implements IRegistryEntry {
	
	private final NamespacePath id;
	
	public Team(NamespacePath id) {
		this.id = id;
	}
	
	@Override
	public NamespacePath getID() {
		return id;
	}
}
