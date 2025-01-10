package muscaa.chess.client.registries;

import muscaa.chess.Chess;
import muscaa.chess.client.Client;
import muscaa.chess.client.assets.SoundAsset;
import muscaa.chess.client.events.IRegisterSoundsEventListener;
import muscaa.chess.registry.Registries;
import muscaa.chess.registry.Registry;

public class SoundRegistry {
	
	public static final Registry<SoundAsset> REG = Registries.create(Chess.NAMESPACE.path("sounds"), SoundAsset::dispose);
	
	public static final SoundAsset NULL = REG.register(new SoundAsset(Chess.NAMESPACE.path("null"), null, null));
	public static final SoundAsset GAME_START = REG.register(new SoundAsset(Chess.NAMESPACE.path("game_start"), "sounds/game-start.ogg", SoundCategoryRegistry.SOUND));
	public static final SoundAsset GAME_END = REG.register(new SoundAsset(Chess.NAMESPACE.path("game_end"), "sounds/game-end.ogg", SoundCategoryRegistry.SOUND));
	public static final SoundAsset NOTIFY = REG.register(new SoundAsset(Chess.NAMESPACE.path("notify"), "sounds/notify.ogg", SoundCategoryRegistry.SOUND));
	public static final SoundAsset MOVE = REG.register(new SoundAsset(Chess.NAMESPACE.path("move"), "sounds/move.ogg", SoundCategoryRegistry.SOUND));
	public static final SoundAsset CAPTURE = REG.register(new SoundAsset(Chess.NAMESPACE.path("capture"), "sounds/capture.ogg", SoundCategoryRegistry.SOUND));
	public static final SoundAsset CHECK = REG.register(new SoundAsset(Chess.NAMESPACE.path("check"), "sounds/check.ogg", SoundCategoryRegistry.SOUND));
	public static final SoundAsset PROMOTE = REG.register(new SoundAsset(Chess.NAMESPACE.path("promote"), "sounds/promote.ogg", SoundCategoryRegistry.SOUND));
	
	public static final SoundAsset AMBIENT = REG.register(new SoundAsset(Chess.NAMESPACE.path("ambient"), "sounds/ambient.ogg", SoundCategoryRegistry.MUSIC));
	
	public static void init() {
		Client.INSTANCE.getSettings().masterVolume.addListener(v -> updateVolume());
		Client.INSTANCE.getSettings().musicVolume.addListener(v -> updateVolume());
		Client.INSTANCE.getSettings().soundVolume.addListener(v -> updateVolume());
		
		Chess.EVENTS.call(
				IRegisterSoundsEventListener.class,
				IRegisterSoundsEventListener::onRegisterSoundsEvent,
				new IRegisterSoundsEventListener.RegisterSoundsEvent(
						REG
						)
				);
		
		REG.lock();
	}
	
	private static void updateVolume() {
		REG.forEach(SoundAsset::updateVolume);
	}
	
	public static void dispose() {
		REG.dispose();
	}
}
