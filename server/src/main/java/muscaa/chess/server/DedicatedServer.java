package muscaa.chess.server;

import fluff.network.NetworkException;
import muscaa.chess.AbstractServer;
import muscaa.chess.board.AbstractServerBoard;
import muscaa.chess.board.boards.LobbyServerBoard;
import muscaa.chess.network.ChessServer;
import muscaa.chess.network.ServerContextRegistry;
import muscaa.chess.network.play.ServerPlayNetHandler;
import muscaa.chess.server.network.DedicatedServerLoginNetHandler;

public class DedicatedServer extends AbstractServer {
	
	protected final AbstractServerBoard serverBoard;
	
	protected ChessServer networkServer;
	
	public DedicatedServer(AbstractServerBoard serverBoard) {
		this.serverBoard = serverBoard;
		
		addBoard(serverBoard);
	}
	
	public DedicatedServer() {
		this(new LobbyServerBoard());
	}
	
	public void start(int port) {
		start();
		
		networkServer = new ChessServer(port);
		
		ServerContextRegistry.LOGIN.get().setHandlerFunc(() -> new DedicatedServerLoginNetHandler(this));
		ServerContextRegistry.PLAY.get().setHandlerFunc(() -> new ServerPlayNetHandler(this, serverBoard));
		
		try {
			networkServer.start(true);
		} catch (NetworkException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void stop() {
		super.stop();
		
		networkServer.stop();
	}
}
