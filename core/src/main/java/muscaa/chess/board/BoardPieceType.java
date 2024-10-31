package muscaa.chess.board;

import com.badlogic.gdx.graphics.Texture;

import muscaa.chess.assets.Textures;

public enum BoardPieceType {
	WHITE_KING(Textures.WHITE_KING),
	WHITE_QUEEN(Textures.WHITE_QUEEN),
	WHITE_BISHOP(Textures.WHITE_BISHOP),
	WHITE_KNIGHT(Textures.WHITE_KNIGHT),
	WHITE_ROOK(Textures.WHITE_ROOK),
	WHITE_PAWN(Textures.WHITE_PAWN),
	
	BLACK_KING(Textures.BLACK_KING),
	BLACK_QUEEN(Textures.BLACK_QUEEN),
	BLACK_BISHOP(Textures.BLACK_BISHOP),
	BLACK_KNIGHT(Textures.BLACK_KNIGHT),
	BLACK_ROOK(Textures.BLACK_ROOK),
	BLACK_PAWN(Textures.BLACK_PAWN),
	;
	
	private final Texture texture;
	
	private BoardPieceType(Texture texture) {
		this.texture = texture;
	}
	
	public Texture getTexture() {
		return texture;
	}
}
