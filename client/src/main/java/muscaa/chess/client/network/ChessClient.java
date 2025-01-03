package muscaa.chess.client.network;

import java.io.IOException;
import java.net.Socket;

import fluff.network.NetworkException;
import fluff.network.client.AbstractClient;
import fluff.network.packet.IPacketOutbound;
import fluff.network.packet.channels.DefaultPacketChannel;
import muscaa.chess.client.Client;
import muscaa.chess.client.config.ServersConfig;
import muscaa.chess.client.gui.screens.StatusScreen;
import muscaa.chess.client.network.intent.ClientIntentNetHandler;
import muscaa.chess.network.common.packets.PacketDisconnect;
import muscaa.chess.registry.registries.IntentRegistry;

public class ChessClient extends AbstractClient {
	
	private NetworkStatus status;
	private StatusScreen statusScreen;
	
    @SuppressWarnings("resource")
	protected boolean connect(String host, int port) {
    	if (isConnected()) return false;
    	
        try {
        	setContext(ClientContexts.INTENT_CONTEXT, new ClientIntentNetHandler(IntentRegistry.CONNECT));
    		setChannel(new DefaultPacketChannel());
			openConnection(new Socket(host, port));
			
			return isConnected();
		} catch (IOException | NetworkException e) {
			e.printStackTrace();
		}
        return false;
    }
    
	public void connect(ServersConfig.Server server) {
		status = NetworkStatus.CONNECT;
		Client.INSTANCE.getGuiLayer().setScreen(statusScreen = new StatusScreen(status.getText()));
		
		if (isConnected()) disconnect();
		
		if (!connect(server.address, server.port)) {
			disconnect();
		}
	}
	
	public void update(NetworkStatus status) {
		this.status = status;
		
		statusScreen.setText(status.getText());
	}
	
	@Override
	public void send(IPacketOutbound packet) {
		if (!isConnected()) return;
		
		super.send(packet);
	}
	
	@Override
	public void disconnect() {
		if (!isConnected()) return;
		
		super.send(new PacketDisconnect("disconnected"));
		super.disconnect();
	}
}
