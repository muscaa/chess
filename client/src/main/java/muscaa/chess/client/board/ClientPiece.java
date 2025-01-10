package muscaa.chess.client.board;

import muscaa.chess.Chess;
import muscaa.chess.board.TeamValue;
import muscaa.chess.board.piece.IPiece;
import muscaa.chess.client.assets.TextureRegistry;
import muscaa.chess.client.assets.TextureValue;

public class ClientPiece implements IPiece<ClientPieceValue> {
	
	protected final ClientPieceValue registryValue;
	protected final TeamValue team;
	
	public ClientPiece(ClientPieceValue registryValue, TeamValue team) {
		this.registryValue = registryValue;
		this.team = team;
	}
	
	public TextureValue getTexture() {
		if (registryValue == ClientPieceRegistry.NULL.get()) {
			return TextureRegistry.NULL.get();
		}
		return TextureRegistry.REG.get(Chess.NAMESPACE.path(team.getKey().getID().getPath() + "_" + registryValue.getKey().getID().getPath())).get();
	}
	
	@Override
	public ClientPieceValue getRegistryValue() {
		return registryValue;
	}
	
	@Override
	public TeamValue getTeam() {
		return team;
	}
}
