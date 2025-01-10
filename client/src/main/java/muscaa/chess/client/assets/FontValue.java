package muscaa.chess.client.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import muscaa.chess.client.utils.Screen;
import muscaa.chess.registry.IRegistryValue;
import muscaa.chess.registry.RegistryKey;

public class FontValue implements IRegistryValue<FontValue> {
	
	private final RegistryKey<FontValue> key;
	private final BitmapFont font;
	private final GlyphLayout layout = new GlyphLayout();
	
	public FontValue(RegistryKey<FontValue> key, String path, int size) {
		this.key = key;
		
		if (path == null) {
			this.font = null;
		} else {
			FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(path));
			FreeTypeFontParameter parameter = new FreeTypeFontParameter();
			parameter.size = size;
			this.font = generator.generateFont(parameter);
			generator.dispose();
		}
	}
	
	public void draw(String text, float x, float y, Color color) {
		if (font == null) return;
		
		Screen.beginSprites();
		font.setColor(color);
		font.draw(Screen.SPRITES, text, x, y);
		Screen.endSprites();
	}
	
	public void dispose() {
		if (font != null) {
			font.dispose();
		}
	}
	
	public int getWidth(String text) {
		if (font == null) return 0;
		
		layout.setText(font, text);
		
		return (int) layout.width;
	}
	
	public int getHeight() {
		if (font == null) return 0;
		
		return (int) font.getLineHeight();
	}
	
	public BitmapFont getBitmapFont() {
		return font;
	}
	
	@Override
	public RegistryKey<FontValue> getKey() {
		return key;
	}
}
