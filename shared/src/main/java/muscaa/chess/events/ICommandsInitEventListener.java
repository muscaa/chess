package muscaa.chess.events;

import com.mojang.brigadier.CommandDispatcher;

import fluff.events.IEvent;
import fluff.events.IEventListener;
import muscaa.chess.command.ICommandSource;

public interface ICommandsInitEventListener extends IEventListener {
	
	void onCommandsInitEvent(CommandsInitEvent event);
	
	class CommandsInitEvent implements IEvent {
		
		private final CommandDispatcher<ICommandSource> dispatcher;
		
		public CommandsInitEvent(CommandDispatcher<ICommandSource> dispatcher) {
			this.dispatcher = dispatcher;
		}
		
		public CommandDispatcher<ICommandSource> getDispatcher() {
			return dispatcher;
		}
	}
}
