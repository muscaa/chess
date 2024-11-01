package muscaa.chess.server;

import java.io.IOException;
import java.net.Socket;
import java.util.UUID;

import fluff.network.NetworkException;
import fluff.network.server.AbstractServer;

public class ChessServer extends AbstractServer {
	
	public ChessServer(int port) {
		super(port);
	}
	
	@Override
	protected void createConnection(Socket socket) throws IOException, NetworkException {
		ChessClientConnection client = new ChessClientConnection(this,
				UUID.nameUUIDFromBytes(new byte[] { (byte) 254 }), socket, defaultContext, defaultHandlerFunc.invoke(),
				defaultChannelFunc.invoke());
		
		if (!client.isConnected()) client.disconnect();
	}
}
