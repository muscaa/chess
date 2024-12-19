package muscaa.chess.shared;

import java.util.Scanner;

import fluff.network.NetworkException;
import muscaa.chess.shared.board.ServerBoard;
import muscaa.chess.shared.network.ServerNetwork;

public class Server {
	
	public static final Server INSTANCE = new Server();
	
	private ServerBoard board; // TODO boards and lobbies
	private ServerNetwork network;
	
	public void start() throws NetworkException {
		board = new ServerBoard();
		
		network = new ServerNetwork();
		network.start();
		
		// commands?
		System.out.println("Server started. Type 'stop' to stop.");
		Scanner s = new Scanner(System.in);
		while (s.hasNextLine()) {
			String line = s.nextLine();
			if (line.equals("stop")) break;
		}
		s.close();
		
		network.stop();
	}
	
	public ServerBoard getBoard() {
		return board;
	}
	
	public ServerNetwork getNetwork() {
		return network;
	}
}
