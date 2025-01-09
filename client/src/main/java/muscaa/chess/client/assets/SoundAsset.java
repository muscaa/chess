package muscaa.chess.client.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import muscaa.chess.registry.IRegistryEntry;
import muscaa.chess.utils.NamespacePath;

public class SoundAsset implements IRegistryEntry {
	
	private final NamespacePath id;
	private final Sound sound;
	
	public SoundAsset(NamespacePath id, String path) {
		this.id = id;
		this.sound = path == null ? null : Gdx.audio.newSound(Gdx.files.internal(path));
	}
	
	public void play() {
		if (sound == null) return;
		
		sound.play();
	}
	
	public void dispose() {
		if (sound == null) return;
		
		sound.dispose();
	}
	
	@Override
	public NamespacePath getID() {
		return id;
	}
}
