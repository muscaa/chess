package muscaa.chess;

import java.io.IOException;
import java.io.InputStream;

import fluff.events.EventManager;
import muscaa.chess.board.HighlightRegistry;
import muscaa.chess.board.TeamRegistry;
import muscaa.chess.board.piece.ServerPieceRegistry;
import muscaa.chess.board.piece.move.MoveRegistry;
import muscaa.chess.command.CommandRegistry;
import muscaa.chess.network.IntentRegistry;
import muscaa.chess.utils.Namespace;

public class Chess {
	
	public static final Namespace NAMESPACE = Namespace.of("chess");
	public static final String VERSION = getVersion();
	public static final int PROTOCOL_VERSION = 2;
	
	public static final EventManager EVENTS = new EventManager();
	
	public static void init() {
		IntentRegistry.init();
		HighlightRegistry.init();
		TeamRegistry.init();
		ServerPieceRegistry.init();
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
