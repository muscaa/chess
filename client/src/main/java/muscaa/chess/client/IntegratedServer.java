package muscaa.chess.client;

import fluff.network.NetworkException;
import muscaa.chess.AbstractServer;
import muscaa.chess.board.AbstractServerBoard;
import muscaa.chess.board.boards.LobbyServerBoard;
import muscaa.chess.client.board.boards.LocalClientBoard;
import muscaa.chess.client.player.players.LocalClientPlayer;
import muscaa.chess.client.player.players.LocalServerPlayer;
import muscaa.chess.network.ChessServer;
import muscaa.chess.network.ServerContextRegistry;
import muscaa.chess.network.login.ServerLoginNetHandler;
import muscaa.chess.network.play.ServerPlayNetHandler;

public class IntegratedServer extends AbstractServer {
	
	public final AbstractServerBoard serverBoard;
	protected final LocalServerPlayer serverPlayer;
	protected final LocalClientBoard clientBoard;
	protected final LocalClientPlayer clientPlayer;
	
	protected ChessServer networkServer;
	
	public IntegratedServer(AbstractServerBoard serverBoard, LocalServerPlayer serverPlayer, LocalClientBoard clientBoard, LocalClientPlayer clientPlayer) {
		this.serverBoard = serverBoard;
		this.serverPlayer = serverPlayer;
		this.clientBoard = clientBoard;
		this.clientPlayer = clientPlayer;
		
		addBoard(serverBoard);
		
		clientBoard.serverPlayer = serverPlayer;
		clientPlayer.server = this;
		clientPlayer.serverPlayer = serverPlayer;
		
		serverPlayer.clientPlayer = clientPlayer;
		serverPlayer.clientBoard = clientBoard;
	}
	
	public IntegratedServer() {
		this(new LobbyServerBoard(), new LocalServerPlayer(), new LocalClientBoard(), new LocalClientPlayer());
	}
	
	public void joinAs(String name) {
		serverPlayer.setName(name);
		
		Client.INSTANCE.setPlayer(clientPlayer);
		Client.INSTANCE.getPlayer().setBoard(clientBoard);
		
		addPlayer(serverPlayer);
		serverBoard.join(serverPlayer, false);
	}
	
	public void openToLan(int port) throws NetworkException {
		networkServer = new ChessServer(port);
		ServerContextRegistry.LOGIN.get().setHandlerFunc(() -> new ServerLoginNetHandler(this));
		ServerContextRegistry.PLAY.get().setHandlerFunc(() -> new ServerPlayNetHandler(this, serverBoard));
		
		networkServer.start(true);
	}
	
	@Override
	public void stop() {
		super.stop();
		
		if (networkServer != null) {
			networkServer.stop();
		}
	}
}
