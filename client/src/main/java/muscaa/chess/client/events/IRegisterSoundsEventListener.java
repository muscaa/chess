package muscaa.chess.client.events;

import fluff.events.IEventListener;
import muscaa.chess.client.assets.SoundAsset;
import muscaa.chess.events.RegistryEvent;
import muscaa.chess.registry.Registry;

public interface IRegisterSoundsEventListener extends IEventListener {
	
	void onRegisterSoundsEvent(RegisterSoundsEvent event);
	
	class RegisterSoundsEvent extends RegistryEvent<SoundAsset> {
		
		public RegisterSoundsEvent(Registry<SoundAsset> reg) {
			super(reg);
		}
	}
}
