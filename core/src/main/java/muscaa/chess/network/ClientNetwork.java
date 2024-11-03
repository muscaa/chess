package muscaa.chess.network;

import muscaa.chess.ChessGame;
import muscaa.chess.gui.screens.StatusScreen;

public class ClientNetwork {
	
	private final ChessClient client;
	
	private String name;
	private NetworkStatus status;
	private StatusScreen statusScreen;
	
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
		
		// wait for opponent
		/*if (status == NetworkStatus.DONE) {
			TaskManager.render(() -> {
				ChessGame.INSTANCE.getGuiLayer().setScreen(null);
			});
		}*/
	}
	
	public void disconnect() {
		client.disconnect();
	}
	
	public ChessClient getClient() {
		return client;
	}
	
	public String getName() {
		return name;
	}
}
