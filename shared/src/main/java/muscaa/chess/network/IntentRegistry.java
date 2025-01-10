package muscaa.chess.network;

import muscaa.chess.Chess;
import muscaa.chess.registry.Registries;
import muscaa.chess.registry.Registry;
import muscaa.chess.registry.RegistryKey;

public class IntentRegistry {
	
	public static final Registry<IntentValue> REG = Registries.create(Chess.NAMESPACE.path("intents"));
	
	//public static final Intent PING = REG.register(new Intent(Chess.NAMESPACE.path("ping")));
	public static final RegistryKey<IntentValue> CONNECT = REG.register(Chess.NAMESPACE.path("connect"), IntentValue::new);
	
	public static void init() {
		REG.init();
	}
}
