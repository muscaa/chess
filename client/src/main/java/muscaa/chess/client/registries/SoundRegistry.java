package muscaa.chess.client.registries;

import muscaa.chess.Chess;
import muscaa.chess.client.assets.SoundAsset;
import muscaa.chess.client.events.IRegisterSoundsEventListener;
import muscaa.chess.registry.Registries;
import muscaa.chess.registry.Registry;

public class SoundRegistry {
	
	public static final Registry<SoundAsset> REG = Registries.create(Chess.NAMESPACE.path("sounds"), SoundAsset::dispose);
	
	public static final SoundAsset NULL = REG.register(new SoundAsset(Chess.NAMESPACE.path("null"), null));
	public static final SoundAsset GAME_START = REG.register(new SoundAsset(Chess.NAMESPACE.path("game_start"), "sounds/game-start.ogg"));
	public static final SoundAsset GAME_END = REG.register(new SoundAsset(Chess.NAMESPACE.path("game_end"), "sounds/game-end.ogg"));
	public static final SoundAsset NOTIFY = REG.register(new SoundAsset(Chess.NAMESPACE.path("notify"), "sounds/notify.ogg"));
	public static final SoundAsset MOVE = REG.register(new SoundAsset(Chess.NAMESPACE.path("move"), "sounds/move.ogg"));
	public static final SoundAsset CAPTURE = REG.register(new SoundAsset(Chess.NAMESPACE.path("capture"), "sounds/capture.ogg"));
	public static final SoundAsset CHECK = REG.register(new SoundAsset(Chess.NAMESPACE.path("check"), "sounds/check.ogg"));
	public static final SoundAsset PROMOTE = REG.register(new SoundAsset(Chess.NAMESPACE.path("promote"), "sounds/promote.ogg"));
	
	public static final SoundAsset AMBIENT = REG.register(new SoundAsset(Chess.NAMESPACE.path("ambient"), "sounds/ambient.ogg"));
	
	public static void init() {
		Chess.EVENTS.call(
				IRegisterSoundsEventListener.class,
				IRegisterSoundsEventListener::onRegisterSoundsEvent,
				new IRegisterSoundsEventListener.RegisterSoundsEvent(
						REG
						)
				);
		
		REG.lock();
	}
	
	public static void dispose() {
		REG.dispose();
	}
}
