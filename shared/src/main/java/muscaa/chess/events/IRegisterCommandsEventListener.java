package muscaa.chess.events;

import com.mojang.brigadier.CommandDispatcher;

import fluff.events.IEvent;
import fluff.events.IEventListener;
import muscaa.chess.command.ICommandSource;

public interface IRegisterCommandsEventListener extends IEventListener {
	
	void onRegisterCommandsEvent(RegisterCommandsEvent event);
	
	class RegisterCommandsEvent implements IEvent {
		
		private final CommandDispatcher<ICommandSource> dispatcher;
		
		public RegisterCommandsEvent(CommandDispatcher<ICommandSource> dispatcher) {
			this.dispatcher = dispatcher;
		}
		
		public CommandDispatcher<ICommandSource> getDispatcher() {
			return dispatcher;
		}
	}
}
