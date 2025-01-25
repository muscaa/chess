package muscaa.chess.server;

import fluff.network.NetworkException;
import muscaa.chess.AbstractServer;
import muscaa.chess.board.AbstractServerBoard;
import muscaa.chess.board.boards.LobbyServerBoard;
import muscaa.chess.network.ChessServer;
import muscaa.chess.network.ServerContextRegistry;
import muscaa.chess.network.login.ServerLoginNetHandler;
import muscaa.chess.network.play.ServerPlayNetHandler;

public class DedicatedServer extends AbstractServer {
	
	protected final ChessServer networkServer;
	protected final AbstractServerBoard serverBoard;
	
	public DedicatedServer(int port, AbstractServerBoard serverBoard) {
		this.networkServer = new ChessServer(port);
		this.serverBoard = serverBoard;
		
		addBoard(serverBoard);
	}
	
	public DedicatedServer(int port) {
		this(port, new LobbyServerBoard());
	}
	
	@Override
	public void start() {
		super.start();
		
		ServerContextRegistry.LOGIN.get().setHandlerFunc(() -> new ServerLoginNetHandler(this));
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
