package muscaa.chess.registry.registries;

import muscaa.chess.Chess;
import muscaa.chess.network.Intent;
import muscaa.chess.registry.Registries;
import muscaa.chess.registry.Registry;

public class IntentRegistry {
	
	public static final Registry<Intent> REG = Registries.create(Chess.NAMESPACE.path("intents"));
	
	//public static final Intent PING = REG.register(new Intent(Chess.NAMESPACE.path("ping")));
	public static final Intent CONNECT = REG.register(new Intent(Chess.NAMESPACE.path("connect")));
	
	public static void init() {
		// TODO call event
		
		REG.lock();
	}
}
