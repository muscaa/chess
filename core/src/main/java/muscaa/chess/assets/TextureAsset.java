package muscaa.chess.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import muscaa.chess.render.Screen;
import muscaa.chess.shared.registry.IRegistryEntry;
import muscaa.chess.shared.utils.NamespacePath;

public class TextureAsset implements IRegistryEntry {
	
	private final NamespacePath id;
	private final Texture texture;
	
	public TextureAsset(NamespacePath id, String path) {
		this.id = id;
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
	
	@Override
	public NamespacePath getID() {
		return id;
	}
}
