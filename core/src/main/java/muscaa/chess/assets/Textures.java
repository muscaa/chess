package muscaa.chess.assets;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import muscaa.chess.render.Screen;

public class Textures {
	
	private static final List<Texture> REG = new LinkedList<>();
	
	public static final Texture WHITE_KING = create("pieces/white_king.png");
	public static final Texture WHITE_QUEEN = create("pieces/white_queen.png");
	public static final Texture WHITE_BISHOP = create("pieces/white_bishop.png");
	public static final Texture WHITE_KNIGHT = create("pieces/white_knight.png");
	public static final Texture WHITE_ROOK = create("pieces/white_rook.png");
	public static final Texture WHITE_PAWN = create("pieces/white_pawn.png");
	public static final Texture BLACK_KING = create("pieces/black_king.png");
	public static final Texture BLACK_QUEEN = create("pieces/black_queen.png");
	public static final Texture BLACK_BISHOP = create("pieces/black_bishop.png");
	public static final Texture BLACK_KNIGHT = create("pieces/black_knight.png");
	public static final Texture BLACK_ROOK = create("pieces/black_rook.png");
	public static final Texture BLACK_PAWN = create("pieces/black_pawn.png");
	
	private static Texture create(String path) {
		Texture t = new Texture(Gdx.files.internal(path));
		REG.add(t);
		return t;
	}
	
	public static void draw(Texture texture, float x, float y, float width, float height) {
		Screen.beginSprites();
		Screen.SPRITES.draw(texture, x, y, width, height);
		Screen.endSprites();
	}
	
	public static void init() {}
	
	public static void dispose() {
		for (Texture t : REG) {
			t.dispose();
		}
		REG.clear();
	}
}
