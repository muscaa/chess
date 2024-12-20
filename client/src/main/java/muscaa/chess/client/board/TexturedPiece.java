package muscaa.chess.client.board;

import muscaa.chess.Chess;
import muscaa.chess.board.Team;
import muscaa.chess.board.piece.IPiece;
import muscaa.chess.board.piece.PieceEntry;
import muscaa.chess.client.assets.TextureAsset;
import muscaa.chess.client.registries.TextureRegistry;
import muscaa.chess.client.registries.TexturedPieceRegistry;

public class TexturedPiece implements IPiece {
	
	protected final PieceEntry<?> registryEntry;
	protected final Team team;
	
	public TexturedPiece(PieceEntry<?> registryEntry, Team team) {
		this.registryEntry = registryEntry;
		this.team = team;
	}
	
	public TextureAsset getTexture() {
		if (registryEntry == TexturedPieceRegistry.NULL) {
			return TextureRegistry.NULL;
		}
		return TextureRegistry.REG.get(Chess.NAMESPACE.path(team.getID().getPath() + "_" + registryEntry.getID().getPath()));
	}
	
	@Override
	public PieceEntry<?> getRegistryEntry() {
		return registryEntry;
	}
	
	@Override
	public Team getTeam() {
		return team;
	}
}
