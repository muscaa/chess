package muscaa.chess.events;

import fluff.events.IEvent;
import muscaa.chess.registry.IRegistryEntry;
import muscaa.chess.registry.Registry;

public class RegistryEvent<E extends IRegistryEntry> implements IEvent {
	
	private final Registry<E> reg;
	
	public RegistryEvent(Registry<E> reg) {
		this.reg = reg;
	}
	
	public Registry<E> getRegistry() {
		return reg;
	}
}
