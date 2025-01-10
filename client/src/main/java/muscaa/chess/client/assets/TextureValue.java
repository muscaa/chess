package muscaa.chess.client.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import muscaa.chess.client.utils.Screen;
import muscaa.chess.registry.IRegistryValue;
import muscaa.chess.registry.RegistryKey;

public class TextureValue implements IRegistryValue<TextureValue> {
	
	private final RegistryKey<TextureValue> key;
	private final Texture texture;
	
	public TextureValue(RegistryKey<TextureValue> key, String path) {
		this.key = key;
		this.texture = path == null ? null : new Texture(Gdx.files.internal(path));
	}
	
	public void draw(float x, float y, float width, float height) {
		if (texture == null) return;
		
		Screen.beginSprites();
		Screen.SPRITES.draw(texture, x, y, width, height);
		Screen.endSprites();
	}
	
	public void dispose() {
		if (texture == null) return;
		
		texture.dispose();
	}
	
	public int getWidth() {
		return texture.getWidth();
	}
	
	public int getHeight() {
		return texture.getHeight();
	}
	
	@Override
	public RegistryKey<TextureValue> getKey() {
		return key;
	}
}
