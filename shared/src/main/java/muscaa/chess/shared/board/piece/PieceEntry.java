package muscaa.chess.shared.board.piece;

import fluff.functions.gen.obj.obj.Func2;
import muscaa.chess.shared.board.Team;
import muscaa.chess.shared.registry.IRegistryEntry;
import muscaa.chess.shared.utils.NamespacePath;

public class PieceEntry<P extends IPiece> implements IRegistryEntry {
	
	private final NamespacePath id;
	private final Func2<P, PieceEntry<P>, Team> func;
	
	public PieceEntry(NamespacePath id, Func2<P, PieceEntry<P>, Team> func) {
		this.id = id;
		this.func = func;
	}
	
	public P create(Team team) {
		return func.invoke(this, team);
	}
	
	@Override
	public NamespacePath getID() {
		return id;
	}
}
