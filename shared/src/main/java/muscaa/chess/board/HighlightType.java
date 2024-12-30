package muscaa.chess.board;

import muscaa.chess.registry.IRegistryEntry;
import muscaa.chess.utils.NamespacePath;

public class HighlightType implements IRegistryEntry {
	
	private final NamespacePath id;
	
	public HighlightType(NamespacePath id) {
		this.id = id;
	}
	
	@Override
	public NamespacePath getID() {
		return id;
	}
}
