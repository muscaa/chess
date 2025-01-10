package muscaa.chess.client.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import muscaa.chess.registry.IRegistryValue;
import muscaa.chess.registry.RegistryKey;

public class SoundValue implements IRegistryValue<SoundValue> {
	
	private final RegistryKey<SoundValue> key;
	private final Sound sound;
	private final SoundCategoryValue category;
	
	private long playingID = -1;
	
	public SoundValue(RegistryKey<SoundValue> key, String path, SoundCategoryValue category) {
		this.key = key;
		this.sound = path == null ? null : Gdx.audio.newSound(Gdx.files.internal(path));
		this.category = category;
	}
	
	public void playSingle() {
		stop();
		play();
	}
	
	public void play() {
		if (category == null) return;
		
		play(category.getVolume());
	}
	
	public void play(float volume) {
		if (sound == null) return;
		
		playingID = sound.play(volume);
	}
	
	public void loopSingle() {
		stop();
		loop();
	}
	
	public void loop() {
		if (category == null) return;
		
		loop(category.getVolume());
	}
	
	public void loop(float volume) {
		if (sound == null) return;
		
		playingID = sound.loop(volume);
	}
	
	public void stop() {
		if (sound == null) return;
		
		sound.stop();
		playingID = -1;
	}
	
	public void updateVolume() {
		if (category == null) return;
		if (playingID == -1) return;
		
		sound.setVolume(playingID, category.getVolume());
	}
	
	public void dispose() {
		if (sound == null) return;
		
		sound.dispose();
	}
	
	public SoundCategoryValue getCategory() {
		return category;
	}
	
	@Override
	public RegistryKey<SoundValue> getKey() {
		return key;
	}
}
