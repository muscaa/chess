package muscaa.chess.registry.registries;

import muscaa.chess.Chess;
import muscaa.chess.board.HighlightType;
import muscaa.chess.registry.Registries;
import muscaa.chess.registry.Registry;

public class HighlightRegistry {
	
	public static final Registry<HighlightType> REG = Registries.create(Chess.NAMESPACE.path("highlights"));
	
	public static final HighlightType SELECTED = REG.register(new HighlightType(Chess.NAMESPACE.path("selected")));
	public static final HighlightType MOVE_AVAILABLE = REG.register(new HighlightType(Chess.NAMESPACE.path("move_available")));
	public static final HighlightType CHECK = REG.register(new HighlightType(Chess.NAMESPACE.path("check")));
	public static final HighlightType LAST_MOVE = REG.register(new HighlightType(Chess.NAMESPACE.path("last_move")));
	
	public static void init() {
		// TODO call event
		
		REG.lock();
	}
}
