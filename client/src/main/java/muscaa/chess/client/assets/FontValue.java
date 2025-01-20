package muscaa.chess.client.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import muscaa.chess.chat.ChatColor;
import muscaa.chess.chat.ChatComponent;
import muscaa.chess.chat.ChatComponentColor;
import muscaa.chess.chat.ChatUtils;
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
	
	public void draw(String text, float x, float y, ChatColor color) {
		if (font == null) return;
		
		drawComponent(ChatUtils.parse(text, color), x, y);
	}
	
	public void drawFormatted(String text, float x, float y, ChatColor color) {
		if (font == null) return;
		
		drawComponent(ChatUtils.parse(text, color, ChatUtils.FORMAT_CHAR), x, y);
	}
	
	public void drawComponent(ChatComponent component, float x, float y) {
		if (font == null) return;
		
		BitmapFontCache cache = begin();
		cache.addText(component.text(), x, y);
		for (ChatComponentColor componentColor : component.colors()) {
			cache.setColors(componentColor.color().getFloatBits(), componentColor.from(), componentColor.to());
		}
		end();
	}
	
	private BitmapFontCache begin() {
		Screen.beginSprites();
		
		BitmapFontCache cache = font.getCache();
		cache.clear();
		return cache;
	}
	
	private void end() {
		font.getCache().draw(Screen.SPRITES);
		
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
