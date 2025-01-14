package muscaa.chess.events;

import fluff.events.EventStage;
import fluff.events.IEvent;
import fluff.events.IEventListener;
import muscaa.chess.registry.Registry;

public interface IRegistryInitEventListener extends IEventListener {
	
	void onRegistryInitEvent(RegistryInitEvent event, EventStage state);
	
	class RegistryInitEvent implements IEvent {
		
		private final Registry<?> reg;
		
		public RegistryInitEvent(Registry<?> reg) {
			this.reg = reg;
		}
		
		public Registry<?> getRegistry() {
			return reg;
		}
	}
}
