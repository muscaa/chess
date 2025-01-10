package muscaa.chess.board.piece;

import fluff.functions.gen.obj.Func1;
import muscaa.chess.board.TeamValue;
import muscaa.chess.registry.RegistryKey;

public class ServerPieceValue extends AbstractPieceValue<ServerPieceValue, AbstractServerPiece> {
	
	private final Func1<AbstractServerPiece, TeamValue> func;
	
	public ServerPieceValue(RegistryKey<ServerPieceValue> key, Func1<AbstractServerPiece, TeamValue> func) {
		super(key);
		
		this.func = func;
	}
	
	@Override
	public AbstractServerPiece create(TeamValue team) {
		return func.invoke(team);
	}
}
