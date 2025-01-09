package muscaa.chess.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import muscaa.chess.Chess;
import muscaa.chess.events.IRegisterCommandsEventListener;

public class CommandRegistry {
	
	public static final CommandDispatcher<ICommandSource> DISPATCHER = new CommandDispatcher<>();
	
	public static void init() {
		DISPATCHER.register(
				LiteralArgumentBuilder.<ICommandSource>literal("ping")
				.executes(context -> {
					System.out.println("pong");
					return 0;
				})
				);
		
		Chess.EVENTS.call(
				IRegisterCommandsEventListener.class,
				IRegisterCommandsEventListener::onRegisterCommandsEvent,
				new IRegisterCommandsEventListener.RegisterCommandsEvent(
						DISPATCHER
						)
				);
	}
}
