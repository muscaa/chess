package muscaa.chess.client.events;

import fluff.events.IEventListener;
import muscaa.chess.client.assets.SoundCategory;
import muscaa.chess.events.RegistryEvent;
import muscaa.chess.registry.Registry;

public interface IRegisterSoundCategoriesEventListener extends IEventListener {
	
	void onRegisterSoundCategoriesEvent(RegisterSoundCategoriesEvent event);
	
	class RegisterSoundCategoriesEvent extends RegistryEvent<SoundCategory> {
		
		public RegisterSoundCategoriesEvent(Registry<SoundCategory> reg) {
			super(reg);
		}
	}
}
