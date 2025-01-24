package muscaa.chess.server;

import muscaa.chess.AbstractServer;
import muscaa.chess.board.boards.LobbyServerBoard;
import muscaa.chess.network.ChessServer;
import muscaa.chess.network.ServerContextRegistry;
import muscaa.chess.network.play.ServerPlayNetHandler;

public class DedicatedServer extends AbstractServer {
	
	public final ChessServer server;
	public final LobbyServerBoard board;
	
	public DedicatedServer() {
		server = new ChessServer(40755);
		board = new LobbyServerBoard();
		ServerContextRegistry.PLAY.get().setHandlerFunc(() -> new ServerPlayNetHandler(this, board));
		
		addBoard(board);
	}
}
