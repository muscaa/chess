package muscaa.chess.server;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import fluff.network.NetworkException;
import muscaa.chess.server.board.ServerBoard;
import muscaa.chess.server.network.ChessClientConnection;
import muscaa.chess.server.network.ServerNetwork;
import muscaa.chess.shared.board.ChessColor;

public class GameServer {
	
	public static final GameServer INSTANCE = new GameServer();
	
	private ServerBoard board;
	private ServerNetwork network;
	
	public ChessColor turn = ChessColor.WHITE;
	public Map<ChessColor, ChessClientConnection> players = new HashMap<>();
	public Map<ChessClientConnection, ChessColor> colors = new HashMap<>();
	
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
