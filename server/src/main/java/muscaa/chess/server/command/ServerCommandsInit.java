package muscaa.chess.server.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;

import muscaa.chess.command.ICommandSource;
import muscaa.chess.events.ICommandsInitEventListener;
import muscaa.chess.server.Server;

public class ServerCommandsInit implements ICommandsInitEventListener {
	
	@Override
	public void onCommandsInitEvent(CommandsInitEvent event) {
		CommandDispatcher<ICommandSource> dispatcher = event.getDispatcher();
		
		dispatcher.register(
				LiteralArgumentBuilder.<ICommandSource>literal("stop")
				.executes(context -> {
					context.getSource().addChatLine("Stopping server...");
					Server.INSTANCE.stop();
					return 0;
				})
		);
		
		dispatcher.register(
				LiteralArgumentBuilder.<ICommandSource>literal("broadcast")
				.then(RequiredArgumentBuilder.<ICommandSource, String>argument("message", StringArgumentType.greedyString())
						.executes(context -> {
							String message = context.getArgument("message", String.class);
							context.getSource().getServer().broadcast(message);
							return 0;
						})
				)
		);
	}
}
