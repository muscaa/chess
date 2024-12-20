package muscaa.chess.client.network;

import java.io.IOException;
import java.net.Socket;

import fluff.network.NetworkException;
import fluff.network.client.AbstractClient;
import fluff.network.packet.channels.DefaultPacketChannel;
import muscaa.chess.client.network.connection.ClientConnectionNetHandler;

public class ChessClient extends AbstractClient {
	
    @SuppressWarnings("resource")
	protected boolean connect(String host, int port) {
    	if (isConnected()) return false;
    	
        try {
        	setContext(ClientContexts.CONNECTION_CONTEXT, new ClientConnectionNetHandler());
    		setChannel(new DefaultPacketChannel());
			openConnection(new Socket(host, port));
			
			return isConnected();
		} catch (IOException | NetworkException e) {
			e.printStackTrace();
		}
        return false;
    }
}
