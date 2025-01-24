package muscaa.chess.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import muscaa.chess.Chess;
import muscaa.chess.events.ICommandsInitEventListener;

public class CommandRegistry {
	
	public static final CommandDispatcher<ICommandSource> DISPATCHER = new CommandDispatcher<>();
	
	public static void init() {
		DISPATCHER.register(
				LiteralArgumentBuilder.<ICommandSource>literal("ping")
				.executes(context -> {
					context.getSource().addChatLine("Pong!");
					return 0;
				})
				);
		
		Chess.EVENTS.call(
				ICommandsInitEventListener.class,
				ICommandsInitEventListener::onCommandsInitEvent,
				new ICommandsInitEventListener.CommandsInitEvent(
						DISPATCHER
						)
				);
	}
}
