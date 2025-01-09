package muscaa.chess;

import fluff.network.NetworkException;
import muscaa.chess.board.ServerBoard;
import muscaa.chess.network.ServerNetwork;

public class Server {
	
	public static final Server INSTANCE = new Server();
	
	private ServerBoard board; // TODO boards and lobbies
	private ServerNetwork network;
	
	public void start() throws NetworkException {
		board = new ServerBoard();
		
		network = new ServerNetwork();
		network.start();
	}
	
	public void stop() {
		network.stop();
	}
	
	public ServerBoard getBoard() {
		return board;
	}
	
	public ServerNetwork getNetwork() {
		return network;
	}
}
