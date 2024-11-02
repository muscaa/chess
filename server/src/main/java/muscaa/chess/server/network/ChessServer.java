package muscaa.chess.server.network;

import java.io.IOException;
import java.net.Socket;

import fluff.network.NetworkException;
import fluff.network.server.AbstractServer;
import fluff.network.server.IClientConnection;

public class ChessServer extends AbstractServer {
	
	public ChessServer(int port) {
		super(port);
	}
	
	@Override
	protected void createConnection(Socket socket) throws IOException, NetworkException {
		ChessClientConnection client = new ChessClientConnection(this, socket,
				defaultContext, defaultHandlerFunc.invoke(), defaultChannelFunc.invoke());
		
		if (!client.isConnected()) client.disconnect();
	}
	
	@Override
	protected void onConnect(IClientConnection client) throws NetworkException {
		System.out.println(client.hashCode() + " connected");
		
		super.onConnect(client);
	}
	
	@Override
	protected void onDisconnect(IClientConnection client) {
		System.out.println(client.hashCode() + " disconnected");
		
		super.onDisconnect(client);
	}
	
	@Override
	protected boolean establishConnection(IClientConnection client) {
		boolean result = super.establishConnection(client);
		System.out.println("timedout: " + !result);
		return result;
	}
}
