package muscaa.chess.client.events;

import fluff.events.IEventListener;
import muscaa.chess.client.assets.TextureAsset;
import muscaa.chess.events.RegistryEvent;
import muscaa.chess.registry.Registry;

public interface IRegisterTexturesEventListener extends IEventListener {
	
	void onRegisterTexturesEvent(RegisterTexturesEvent event);
	
	class RegisterTexturesEvent extends RegistryEvent<TextureAsset> {
		
		public RegisterTexturesEvent(Registry<TextureAsset> reg) {
			super(reg);
		}
	}
}
