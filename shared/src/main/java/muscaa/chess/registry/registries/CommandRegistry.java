package muscaa.chess.registry.registries;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

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
	}
	
	public static interface ICommandSource {
		
	}
}
