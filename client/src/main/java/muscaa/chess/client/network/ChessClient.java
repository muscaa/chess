package muscaa.chess.client.network;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import fluff.network.NetworkException;
import fluff.network.client.AbstractClient;
import fluff.network.client.ClientErrorType;
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
	protected boolean connect(String host, int port) throws UnknownHostException, IOException, NetworkException {
    	if (isConnected()) return false;
    	
    	setContext(ClientContexts.INTENT_CONTEXT, new ClientIntentNetHandler(IntentRegistry.CONNECT));
		setChannel(new DefaultPacketChannel());
		openConnection(new Socket(host, port));
		
		return isConnected();
    }
    
	public void connect(ServersConfig.Server server) throws UnknownHostException, IOException, NetworkException {
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
	
	@Override
	protected void onError(ClientErrorType type, Exception e) {
        switch (type) {
	        case CONNECTION:
	            super.disconnect();
	            break;
	        case READ:
	            disconnect();
	            break;
	        case WRITE:
	            // nothing
	            break;
	    }
	}
}
