package muscaa.chess.client.board;

import muscaa.chess.board.TeamValue;
import muscaa.chess.board.piece.AbstractPieceValue;
import muscaa.chess.registry.RegistryKey;

public class ClientPieceValue extends AbstractPieceValue<ClientPieceValue, ClientPiece> {
	
	public ClientPieceValue(RegistryKey<ClientPieceValue> key) {
		super(key);
	}
	
	@Override
	public ClientPiece create(TeamValue team) {
		return new ClientPiece(this, team);
	}
}
