package muscaa.chess.network;

import muscaa.chess.ChessGame;
import muscaa.chess.board.online.OnlineBoard;
import muscaa.chess.gui.screens.StatusScreen;
import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.task.TaskManager;

public class ClientNetwork {
	
	private final ChessClient client;
	
	private String name;
	private NetworkStatus status;
	private StatusScreen statusScreen;
	
	private OnlineBoard board;
	
	public ClientNetwork() {
		client = new ChessClient();
	}
	
	public void connect(String ip, int port, String name) {
		this.name = name;
		
		status = NetworkStatus.CONNECT;
		ChessGame.INSTANCE.getGuiLayer().setScreen(statusScreen = new StatusScreen(status.getText()));
		
		if (client.isConnected()) disconnect();
		
		if (!client.connect(ip, port)) {
			disconnect();
		}
	}
	
	public void update(NetworkStatus status) {
		this.status = status;
		
		statusScreen.label.setText(status.getText());
		
		if (status == NetworkStatus.DONE) {
			TaskManager.render(() -> {
				ChessGame.INSTANCE.getGuiLayer().setScreen(null);
			});
		}
	}
	
	public void createBoard(ChessColor color) {
		ChessGame.INSTANCE.getBoardLayer().setBoard(board = new OnlineBoard(color));
	}
	
	public void disconnect() {
		ChessGame.INSTANCE.getBoardLayer().setBoard(board = null);
		
		client.disconnect();
	}
	
	public ChessClient getClient() {
		return client;
	}
	
	public String getName() {
		return name;
	}
	
	public OnlineBoard getBoard() {
		return board;
	}
}
