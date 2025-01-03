package muscaa.chess.client.assets;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import muscaa.chess.client.utils.Screen;

public class Fonts {
	
	private static final List<BitmapFont> REG = new LinkedList<>();
	
	public static final BitmapFont VARELA_24;
	public static final BitmapFont VARELA_18;
	
	public static final BitmapFont MONTEZ_128;
	
	static {
		BitmapFont[] varela = generate("fonts/VarelaRound-Regular.ttf", 24, 18);
		VARELA_24 = varela[0];
		VARELA_18 = varela[1];
		
		BitmapFont[] montez = generate("fonts/Montez-Regular.ttf", 128);
		MONTEZ_128 = montez[0];
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
	
	public static void draw(BitmapFont font, String text, float x, float y, Color color) {
		Screen.beginSprites();
		font.setColor(color);
		font.draw(Screen.SPRITES, text, x, y);
		Screen.endSprites();
	}
	
	public static void init() {}
	
	public static void dispose() {
		REG.clear();
	}
}
