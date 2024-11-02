package muscaa.chess.board;

import com.badlogic.gdx.graphics.Texture;

import muscaa.chess.assets.Textures;
import muscaa.chess.shared.board.IChessPiece;

public enum ClientChessPiece implements IChessPiece {
	WHITE_KING("wk", Textures.WHITE_KING),
	WHITE_QUEEN("wq", Textures.WHITE_QUEEN),
	WHITE_BISHOP("wb", Textures.WHITE_BISHOP),
	WHITE_KNIGHT("wn", Textures.WHITE_KNIGHT),
	WHITE_ROOK("wr", Textures.WHITE_ROOK),
	WHITE_PAWN("wp", Textures.WHITE_PAWN),
	
	BLACK_KING("bk", Textures.BLACK_KING),
	BLACK_QUEEN("bq", Textures.BLACK_QUEEN),
	BLACK_BISHOP("bb", Textures.BLACK_BISHOP),
	BLACK_KNIGHT("bn", Textures.BLACK_KNIGHT),
	BLACK_ROOK("br", Textures.BLACK_ROOK),
	BLACK_PAWN("bp", Textures.BLACK_PAWN),
	;
	
	private final String id;
	private final Texture texture;
	
	private ClientChessPiece(String id, Texture texture) {
		this.id = id;
		this.texture = texture;
	}
	
	@Override
	public String getID() {
		return id;
	}
	
	public Texture getTexture() {
		return texture;
	}
	
	public static ClientChessPiece of(String id) {
		if (id == null) return null;
		
		for (ClientChessPiece p : ClientChessPiece.values()) {
			if (p.id.equals(id)) return p;
		}
		return null;
	}
}
