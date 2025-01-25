package muscaa.chess.server.events;

import fluff.events.IEvent;
import fluff.events.IEventListener;

public interface IDatabaseInitEventListener extends IEventListener {
	
	void onDatabaseInitEvent(DatabaseInitEvent event);
	
	class DatabaseInitEvent implements IEvent {
	}
}
