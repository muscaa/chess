package muscaa.chess.client.registries;

import muscaa.chess.Chess;
import muscaa.chess.client.Client;
import muscaa.chess.client.assets.SoundCategory;
import muscaa.chess.client.events.IRegisterSoundCategoriesEventListener;
import muscaa.chess.registry.Registries;
import muscaa.chess.registry.Registry;

public class SoundCategoryRegistry {
	
	public static final Registry<SoundCategory> REG = Registries.create(Chess.NAMESPACE.path("sound_categories"));
	
	public static final SoundCategory NULL = REG.register(new SoundCategory(Chess.NAMESPACE.path("null"), null));
	public static final SoundCategory SOUND = REG.register(new SoundCategory(Chess.NAMESPACE.path("sound"), Client.INSTANCE.getSettings().soundVolume::get));
	public static final SoundCategory MUSIC = REG.register(new SoundCategory(Chess.NAMESPACE.path("music"), Client.INSTANCE.getSettings().musicVolume::get));
	
	public static void init() {
		Chess.EVENTS.call(
				IRegisterSoundCategoriesEventListener.class,
				IRegisterSoundCategoriesEventListener::onRegisterSoundCategoriesEvent,
				new IRegisterSoundCategoriesEventListener.RegisterSoundCategoriesEvent(
						REG
						)
				);
		
		REG.lock();
	}
}
