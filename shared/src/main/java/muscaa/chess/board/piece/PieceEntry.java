package muscaa.chess.board.piece;

import fluff.functions.gen.obj.obj.Func2;
import muscaa.chess.board.Team;
import muscaa.chess.registry.IRegistryEntry;
import muscaa.chess.utils.NamespacePath;

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
