package muscaa.chess.network;

import fluff.network.packet.IPacketOutbound;
import muscaa.chess.ChessGame;
import muscaa.chess.gui.screens.StatusScreen;
import muscaa.chess.shared.network.common.packets.PacketDisconnect;

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
	}
	
	public void send(IPacketOutbound packet) {
		if (client.isConnected()) client.send(packet);
	}
	
	public void disconnect() {
		send(new PacketDisconnect("disconnected"));
		client.disconnect();
	}
	
	public String getName() {
		return name;
	}
}
