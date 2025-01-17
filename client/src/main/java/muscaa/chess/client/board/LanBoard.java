package muscaa.chess.client.board;

import fluff.network.NetworkException;
import muscaa.chess.network.ChessServer;
import muscaa.chess.network.ServerContextRegistry;
import muscaa.chess.network.play.ServerPlayNetHandler;

public class LanBoard extends AbstractLocalBoard {
	
	protected final ChessServer server;
	
	public LanBoard(int port) throws NetworkException {
		server = new ChessServer(port);
		
		ServerContextRegistry.PLAY.get().setHandlerFunc(() -> new ServerPlayNetHandler(lobby));
		server.start(true);
	}
	
	@Override
	public void dispose() {
		server.stop();
		
		super.dispose();
	}
}
