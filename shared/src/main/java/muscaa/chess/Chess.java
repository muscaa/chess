package muscaa.chess;

import java.io.IOException;
import java.io.InputStream;

import fluff.events.EventManager;
import muscaa.chess.command.CommandRegistry;
import muscaa.chess.registry.registries.HighlightRegistry;
import muscaa.chess.registry.registries.IntentRegistry;
import muscaa.chess.registry.registries.MoveRegistry;
import muscaa.chess.registry.registries.PieceRegistry;
import muscaa.chess.registry.registries.TeamRegistry;
import muscaa.chess.utils.Namespace;

public class Chess {
	
	public static final Namespace NAMESPACE = Namespace.of("chess");
	public static final String VERSION = getVersion();
	public static final int PROTOCOL_VERSION = 1;
	
	public static final EventManager EVENTS = new EventManager();
	
	public static void init() {
		IntentRegistry.init();
		HighlightRegistry.init();
		TeamRegistry.init();
		PieceRegistry.init();
		MoveRegistry.init();
		CommandRegistry.init();
	}
	
	private static String getVersion() {
		try (InputStream in = Chess.class.getResourceAsStream("/version.txt")) {
			return new String(in.readAllBytes());
		} catch (IOException e) {}
		return "unknown";
	}
}
