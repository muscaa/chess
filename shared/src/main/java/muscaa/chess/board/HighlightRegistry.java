package muscaa.chess.board;

import muscaa.chess.Chess;
import muscaa.chess.registry.Registries;
import muscaa.chess.registry.Registry;
import muscaa.chess.registry.RegistryKey;

public class HighlightRegistry {
	
	public static final Registry<HighlightValue> REG = Registries.create(Chess.NAMESPACE.path("highlights"));
	
	public static final RegistryKey<HighlightValue> NULL = REG.register(Chess.NULL, HighlightValue::new);
	public static final RegistryKey<HighlightValue> SELECTED = REG.register(Chess.NAMESPACE.path("selected"), HighlightValue::new);
	public static final RegistryKey<HighlightValue> MOVE_AVAILABLE = REG.register(Chess.NAMESPACE.path("move_available"), HighlightValue::new);
	public static final RegistryKey<HighlightValue> CHECK = REG.register(Chess.NAMESPACE.path("check"), HighlightValue::new);
	public static final RegistryKey<HighlightValue> LAST_MOVE = REG.register(Chess.NAMESPACE.path("last_move"), HighlightValue::new);
	
	public static void init() {
		REG.init();
	}
}
