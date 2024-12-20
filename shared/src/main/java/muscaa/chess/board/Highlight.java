package muscaa.chess.board;

import muscaa.chess.registry.IRegistryEntry;
import muscaa.chess.utils.NamespacePath;

public class Highlight implements IRegistryEntry {
	
	private final NamespacePath id;
	
	public Highlight(NamespacePath id) {
		this.id = id;
	}
	
	@Override
	public NamespacePath getID() {
		return id;
	}
}
