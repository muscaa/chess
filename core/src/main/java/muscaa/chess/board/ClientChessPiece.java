package muscaa.chess.board;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;

import muscaa.chess.assets.Textures;
import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.shared.board.IChessPiece;

public class ClientChessPiece implements IChessPiece {
	
	private static final Map<Integer, ClientChessPiece> REG = new HashMap<>();
	
	public static final ClientChessPiece EMPTY = new ClientChessPiece(0, ChessColor.NONE, null);
	
	public static final ClientChessPiece WHITE_KING = new ClientChessPiece(1, ChessColor.WHITE, Textures.WHITE_KING);
	public static final ClientChessPiece WHITE_QUEEN = new ClientChessPiece(2, ChessColor.WHITE, Textures.WHITE_QUEEN);
	public static final ClientChessPiece WHITE_BISHOP = new ClientChessPiece(3, ChessColor.WHITE, Textures.WHITE_BISHOP);
	public static final ClientChessPiece WHITE_KNIGHT = new ClientChessPiece(4, ChessColor.WHITE, Textures.WHITE_KNIGHT);
	public static final ClientChessPiece WHITE_ROOK = new ClientChessPiece(5, ChessColor.WHITE, Textures.WHITE_ROOK);
	public static final ClientChessPiece WHITE_PAWN = new ClientChessPiece(6, ChessColor.WHITE, Textures.WHITE_PAWN);
	
	public static final ClientChessPiece BLACK_KING = new ClientChessPiece(-1, ChessColor.BLACK, Textures.BLACK_KING);
	public static final ClientChessPiece BLACK_QUEEN = new ClientChessPiece(-2, ChessColor.BLACK, Textures.BLACK_QUEEN);
	public static final ClientChessPiece BLACK_BISHOP = new ClientChessPiece(-3, ChessColor.BLACK, Textures.BLACK_BISHOP);
	public static final ClientChessPiece BLACK_KNIGHT = new ClientChessPiece(-4, ChessColor.BLACK, Textures.BLACK_KNIGHT);
	public static final ClientChessPiece BLACK_ROOK = new ClientChessPiece(-5, ChessColor.BLACK, Textures.BLACK_ROOK);
	public static final ClientChessPiece BLACK_PAWN = new ClientChessPiece(-6, ChessColor.BLACK, Textures.BLACK_PAWN);
	
	protected final int id;
	protected final ChessColor color;
	protected final Texture texture;
	
	public ClientChessPiece(int id, ChessColor color, Texture texture) {
		this.id = id;
		this.color = color;
		this.texture = texture;
		
		REG.put(id, this);
	}
	
	@Override
	public int getID() {
		return id;
	}
	
	@Override
	public ChessColor getColor() {
		return color;
	}
	
	@Override
	public IChessPiece copy() {
		return this;
	}
	
	public Texture getTexture() {
		return texture;
	}
	
	public static ClientChessPiece of(int id) {
		return REG.get(id);
	}
}
