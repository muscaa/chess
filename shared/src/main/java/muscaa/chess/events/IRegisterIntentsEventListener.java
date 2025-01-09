package muscaa.chess.events;

import fluff.events.IEventListener;
import muscaa.chess.network.Intent;
import muscaa.chess.registry.Registry;

public interface IRegisterIntentsEventListener extends IEventListener {
	
	void onRegisterIntentsEvent(RegisterIntentsEvent event);
	
	class RegisterIntentsEvent extends RegistryEvent<Intent> {
		
		public RegisterIntentsEvent(Registry<Intent> reg) {
			super(reg);
		}
	}
}
