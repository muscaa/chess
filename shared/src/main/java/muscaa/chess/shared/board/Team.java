package muscaa.chess.shared.board;

import muscaa.chess.shared.registry.IRegistryEntry;
import muscaa.chess.shared.utils.NamespacePath;

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
