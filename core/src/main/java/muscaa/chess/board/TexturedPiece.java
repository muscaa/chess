package muscaa.chess.board;

import muscaa.chess.assets.TextureAsset;
import muscaa.chess.registries.TextureRegistry;
import muscaa.chess.registries.TexturedPieceRegistry;
import muscaa.chess.shared.Chess;
import muscaa.chess.shared.board.Team;
import muscaa.chess.shared.board.piece.IPiece;
import muscaa.chess.shared.board.piece.PieceEntry;

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
