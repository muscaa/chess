package muscaa.chess.shared.board.piece.move;

import muscaa.chess.shared.board.Cell;
import muscaa.chess.shared.board.matrix.Matrix;
import muscaa.chess.shared.registry.IRegistryEntry;
import muscaa.chess.shared.utils.NamespacePath;

public abstract class AbstractMove implements IRegistryEntry {
	
	private final NamespacePath id;
	
	public AbstractMove(NamespacePath id) {
		this.id = id;
	}
	
	public abstract void doMove(Matrix matrix, Cell from, Cell to);
	
	@Override
	public NamespacePath getID() {
		return id;
	}
}
