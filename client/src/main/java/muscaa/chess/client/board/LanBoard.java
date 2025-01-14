package muscaa.chess.client.board;

import fluff.network.NetworkException;
import muscaa.chess.board.Cell;
import muscaa.chess.board.Lobby;
import muscaa.chess.board.player.AbstractPlayer;
import muscaa.chess.client.Client;
import muscaa.chess.client.board.player.LocalPlayer;
import muscaa.chess.network.ChessServer;
import muscaa.chess.network.ServerContextRegistry;
import muscaa.chess.network.play.ServerPlayNetHandler;

public class LanBoard extends AbstractBoard {
	
	protected final ChessServer server;
	protected final Lobby lobby;
	protected final AbstractPlayer player;
	
	public LanBoard(int port) throws NetworkException {
		server = new ChessServer(port);
		lobby = new Lobby();
		player = new LocalPlayer(this);
		
		ServerContextRegistry.PLAY.get().setHandlerFunc(() -> new ServerPlayNetHandler(lobby));
		server.start(true);
	}
	
	@Override
	public void init(Client chess, BoardLayer layer) {
		super.init(chess, layer);
		
		lobby.join(player, false);
		
		Client.INSTANCE.setScreen(null);
	}
	
	@Override
	public void click(Cell cell) {
		player.click(cell);
	}
	
	@Override
	public void dispose() {
		server.stop();
		lobby.close();
	}
}
