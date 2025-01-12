package muscaa.chess;

import fluff.network.NetworkException;
import muscaa.chess.board.Lobby;
import muscaa.chess.network.ServerNetwork;

public class Server {
	
	public static final Server INSTANCE = new Server();
	
	private Lobby lobby;
	private ServerNetwork network;
	
	public void start() throws NetworkException {
		lobby = new Lobby();
		
		network = new ServerNetwork();
		network.start();
	}
	
	public void stop() {
		network.stop();
	}
	
	public Lobby getLobby() {
		return lobby;
	}
	
	public ServerNetwork getNetwork() {
		return network;
	}
}
