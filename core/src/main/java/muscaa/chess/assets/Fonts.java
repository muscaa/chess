package muscaa.chess.assets;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import muscaa.chess.render.Screen;

public class Fonts {
	
	private static final List<BitmapFont> REG = new LinkedList<>();
	
	public static final BitmapFont SONO_24;
	
	static {
		BitmapFont[] sono = generate("fonts/Sono-Regular.ttf", 24);
		SONO_24 = sono[0];
	}
	
	private static BitmapFont[] generate(String path, int... sizes) {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(path));
    	
		BitmapFont[] fonts = new BitmapFont[sizes.length];
		for (int i = 0; i < sizes.length; i++) {
			int size = sizes[i];
			
			FreeTypeFontParameter parameter = new FreeTypeFontParameter();
	    	parameter.size = size;
	    	
	    	BitmapFont font = generator.generateFont(parameter);
	    	
	    	REG.add(font);
	    	fonts[i] = font;
		}
		
		generator.dispose();
		
    	return fonts;
	}
	
	public static void draw(BitmapFont font, String text, float x, float y) {
		Screen.beginSprites();
		font.draw(Screen.SPRITES, text, x, y);
		Screen.endSprites();
	}
	
	public static void init() {}
	
	public static void dispose() {
		for (BitmapFont font : REG) {
			font.dispose();
		}
		REG.clear();
	}
}
