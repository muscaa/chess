package muscaa.chess.client.assets;

import muscaa.chess.Chess;
import muscaa.chess.client.Client;
import muscaa.chess.registry.Registries;
import muscaa.chess.registry.Registry;
import muscaa.chess.registry.RegistryKey;

public class SoundRegistry {
	
	public static final Registry<SoundValue> REG = Registries.create(Chess.NAMESPACE.path("sounds"));
	
	public static final RegistryKey<SoundValue> NULL = REG.register(Chess.NAMESPACE.path("null"),
			key -> new SoundValue(key, null, null));
	public static final RegistryKey<SoundValue> GAME_START = REG.register(Chess.NAMESPACE.path("game_start"),
			key -> new SoundValue(key, "sounds/game-start.ogg", SoundCategoryRegistry.SOUND.get()));
	public static final RegistryKey<SoundValue> GAME_END = REG.register(Chess.NAMESPACE.path("game_end"),
			key -> new SoundValue(key, "sounds/game-end.ogg", SoundCategoryRegistry.SOUND.get()));
	public static final RegistryKey<SoundValue> NOTIFY = REG.register(Chess.NAMESPACE.path("notify"),
			key -> new SoundValue(key, "sounds/notify.ogg", SoundCategoryRegistry.SOUND.get()));
	public static final RegistryKey<SoundValue> MOVE = REG.register(Chess.NAMESPACE.path("move"),
			key -> new SoundValue(key, "sounds/move.ogg", SoundCategoryRegistry.SOUND.get()));
	public static final RegistryKey<SoundValue> CAPTURE = REG.register(Chess.NAMESPACE.path("capture"),
			key -> new SoundValue(key, "sounds/capture.ogg", SoundCategoryRegistry.SOUND.get()));
	public static final RegistryKey<SoundValue> CHECK = REG.register(Chess.NAMESPACE.path("check"),
			key -> new SoundValue(key, "sounds/check.ogg", SoundCategoryRegistry.SOUND.get()));
	public static final RegistryKey<SoundValue> PROMOTE = REG.register(Chess.NAMESPACE.path("promote"),
			key -> new SoundValue(key, "sounds/promote.ogg", SoundCategoryRegistry.SOUND.get()));
	public static final RegistryKey<SoundValue> AMBIENT = REG.register(Chess.NAMESPACE.path("ambient"),
			key -> new SoundValue(key, "sounds/ambient.ogg", SoundCategoryRegistry.MUSIC.get()));
	
	public static void init() {
		REG.init();
		
		Client.INSTANCE.settings.masterVolume.addListener(v -> updateVolume());
		Client.INSTANCE.settings.musicVolume.addListener(v -> updateVolume());
		Client.INSTANCE.settings.soundVolume.addListener(v -> updateVolume());
	}
	
	private static void updateVolume() {
		REG.forEach(SoundValue::updateVolume);
	}
	
	public static void dispose() {
		REG.forEach(SoundValue::dispose);
	}
}
