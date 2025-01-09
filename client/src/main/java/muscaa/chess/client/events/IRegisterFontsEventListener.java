package muscaa.chess.client.events;

import fluff.events.IEventListener;
import muscaa.chess.client.assets.FontAsset;
import muscaa.chess.events.RegistryEvent;
import muscaa.chess.registry.Registry;

public interface IRegisterFontsEventListener extends IEventListener {
	
	void onRegisterFontsEvent(RegisterFontsEvent event);
	
	class RegisterFontsEvent extends RegistryEvent<FontAsset> {
		
		public RegisterFontsEvent(Registry<FontAsset> reg) {
			super(reg);
		}
	}
}
