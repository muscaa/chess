package muscaa.chess.client.network;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import fluff.network.NetworkException;
import fluff.network.client.AbstractClient;
import fluff.network.client.ClientErrorType;
import fluff.network.packet.IPacketOutbound;
import fluff.network.packet.channels.DefaultPacketChannel;
import muscaa.chess.client.network.intent.ClientIntentNetHandler;
import muscaa.chess.network.IntentValue;
import muscaa.chess.network.common.packets.PacketDisconnect;

public abstract class AbstractChessClient extends AbstractClient {
	
    @SuppressWarnings("resource")
	protected void connect(String host, int port, IntentValue intent) throws UnknownHostException, IOException, NetworkException {
    	if (isConnected()) disconnect();
    	
    	setContext(ClientContextRegistry.INTENT.get(), new ClientIntentNetHandler(intent));
		setChannel(new DefaultPacketChannel());
		openConnection(new Socket(host, port));
		
		if (!isConnected()) {
			disconnect();
		}
    }
	
	public <V extends IClientNetHandler> void setContext(ClientContextValue<V> context) {
		setContext(context, context.getHandlerFunc().invoke());
	}
	
	public <V extends IClientNetHandler> void setContext(ClientContextValue<V> context, V handler) {
		setContextUnsafe(context.getContext(), handler);
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
