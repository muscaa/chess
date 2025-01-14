package muscaa.chess.client.assets;

import muscaa.chess.Chess;
import muscaa.chess.client.Client;
import muscaa.chess.registry.Registries;
import muscaa.chess.registry.Registry;
import muscaa.chess.registry.RegistryKey;

public class SoundCategoryRegistry {
	
	public static final Registry<SoundCategoryValue> REG = Registries.create(Chess.NAMESPACE.path("sound_categories"));
	
	public static final RegistryKey<SoundCategoryValue> NULL = REG.register(Chess.NULL,
			key -> new SoundCategoryValue(key, null));
	public static final RegistryKey<SoundCategoryValue> SOUND = REG.register(Chess.NAMESPACE.path("sound"),
			key -> new SoundCategoryValue(key, Client.INSTANCE.settingsConfig.soundVolume::get));
	public static final RegistryKey<SoundCategoryValue> MUSIC = REG.register(Chess.NAMESPACE.path("music"),
			key -> new SoundCategoryValue(key, Client.INSTANCE.settingsConfig.musicVolume::get));
	
	public static void init() {
		REG.init();
	}
}
