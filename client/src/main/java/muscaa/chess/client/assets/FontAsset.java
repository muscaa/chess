package muscaa.chess.client.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import muscaa.chess.client.utils.Screen;
import muscaa.chess.registry.IRegistryEntry;
import muscaa.chess.utils.NamespacePath;

public class FontAsset implements IRegistryEntry {
	
	private final NamespacePath id;
	private final FreeTypeFontGenerator generator;
	private final BitmapFont font;
	private final GlyphLayout layout = new GlyphLayout();
	
	public FontAsset(NamespacePath id, String path, int size) {
		this.id = id;
		if (path == null) {
			this.font = null;
            this.generator = null;
		} else {
			this.generator = new FreeTypeFontGenerator(Gdx.files.internal(path));
			this.font = generate(size);
		}
	}
	
	private FontAsset(NamespacePath id, FreeTypeFontGenerator generator, BitmapFont font) {
		this.id = id;
		this.generator = generator;
		this.font = font;
	}
	
	public void draw(String text, float x, float y, Color color) {
		if (font == null) return;
		
		Screen.beginSprites();
		font.setColor(color);
		font.draw(Screen.SPRITES, text, x, y);
		Screen.endSprites();
	}
	
	public FontAsset derive(NamespacePath id, int size) {
		return new FontAsset(id, generator, generate(size));
	}
	
	public void dispose() {
		if (generator != null) {
			generator.dispose();
		}
		if (font != null) {
			font.dispose();
		}
	}
	
	private BitmapFont generate(int size) {
		if (generator == null) return null;
		
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = size;
		
		return generator.generateFont(parameter);
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
	public NamespacePath getID() {
		return id;
	}
}
