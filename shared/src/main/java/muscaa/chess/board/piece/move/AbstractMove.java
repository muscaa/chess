package muscaa.chess.board.piece.move;

import muscaa.chess.board.Cell;
import muscaa.chess.board.matrix.Matrix;
import muscaa.chess.registry.IRegistryEntry;
import muscaa.chess.utils.NamespacePath;

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
