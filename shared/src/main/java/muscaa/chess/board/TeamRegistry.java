package muscaa.chess.board;

import muscaa.chess.Chess;
import muscaa.chess.registry.Registries;
import muscaa.chess.registry.Registry;
import muscaa.chess.registry.RegistryKey;

public class TeamRegistry {
	
	public static final Registry<TeamValue> REG = Registries.create(Chess.NAMESPACE.path("teams"));
	
	public static final RegistryKey<TeamValue> NULL = REG.register(Chess.NAMESPACE.path("null"), TeamValue::new);
	public static final RegistryKey<TeamValue> WHITE = REG.register(Chess.NAMESPACE.path("white"), TeamValue::new);
	public static final RegistryKey<TeamValue> BLACK = REG.register(Chess.NAMESPACE.path("black"), TeamValue::new);
	public static final RegistryKey<TeamValue> SPECTATOR = REG.register(Chess.NAMESPACE.path("spectator"), TeamValue::new);
	
	public static void init() {
		REG.init();
	}
}
