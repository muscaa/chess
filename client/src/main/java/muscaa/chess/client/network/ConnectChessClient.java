package muscaa.chess.client.network;

import java.io.IOException;
import java.net.UnknownHostException;

import fluff.network.NetworkException;
import muscaa.chess.client.Client;
import muscaa.chess.client.config.ServersConfig;
import muscaa.chess.client.gui.screens.DisconnectedScreen;
import muscaa.chess.client.gui.screens.StatusScreen;
import muscaa.chess.client.utils.TaskManager;
import muscaa.chess.network.DisconnectReasonValue;
import muscaa.chess.network.IntentRegistry;

public class ConnectChessClient extends AbstractChessClient {
	
	public NetworkStatus status;
	public StatusScreen statusScreen;
	public DisconnectReasonValue disconnectReason;
	public String disconnectMessage;
	
	public void connect(ServersConfig.Server server) throws UnknownHostException, IOException, NetworkException {
		status = NetworkStatus.CONNECT;
		Client.INSTANCE.setScreen(statusScreen = new StatusScreen(status.getText()));
		
		connect(server.address, server.port, IntentRegistry.CONNECT.get());
	}
	
	public void update(NetworkStatus status) {
		this.status = status;
		
		statusScreen.setText(status.getText());
	}
	
	@Override
	public void onDisconnect() {
		super.onDisconnect();
		
		if (disconnectReason == null) return;
		
		TaskManager.render(() -> {
			Client.INSTANCE.setScreen(new DisconnectedScreen(disconnectMessage));
		});
	}
}
