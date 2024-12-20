package muscaa.chess.registry.registries;

import muscaa.chess.Chess;
import muscaa.chess.board.Highlight;
import muscaa.chess.registry.Registries;
import muscaa.chess.registry.Registry;

public class HighlightRegistry {
	
	public static final Registry<Highlight> REG = Registries.create(Chess.NAMESPACE.path("highlights"));
	
	public static final Highlight SELECTED = REG.register(new Highlight(Chess.NAMESPACE.path("selected")));
	public static final Highlight MOVE_AVAILABLE = REG.register(new Highlight(Chess.NAMESPACE.path("move_available")));
	public static final Highlight CHECK = REG.register(new Highlight(Chess.NAMESPACE.path("check")));
	
	public static void init() {}
}
