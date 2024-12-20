package muscaa.chess.network;

import fluff.network.NetworkException;
import fluff.network.server.AbstractClientConnection;
import fluff.network.server.AbstractServer;
import fluff.network.server.modules.TimeoutModule;
import muscaa.chess.network.common.packets.PacketDisconnect;

public class ChessServer extends AbstractServer {
	
	public ChessServer(int port) {
		super(port);
		
		addModule(new TimeoutModule());
	}
	
	@Override
	protected AbstractClientConnection createConnection() {
		return new ChessClientConnection(this, defaultContext, defaultHandlerFunc.invoke(), defaultChannelFunc.invoke());
	}
	
	@Override
	protected void onConnect(AbstractClientConnection connection) throws NetworkException {
		//System.out.println(connection.hashCode() + " connected");
		
		super.onConnect(connection);
	}
	
	@Override
	protected void onDisconnect(AbstractClientConnection connection) {
		//System.out.println(connection.hashCode() + " disconnected");
		
		super.onDisconnect(connection);
	}
	
	/*@Override
	protected boolean establishConnection(IClientConnection client) {
		boolean result = super.establishConnection(client);
		System.out.println("timedout: " + !result);
		return result;
	}*/
	
	@Override
	public void disconnectAll() {
		sendAll(new PacketDisconnect("Server stopped!"));
		super.disconnectAll();
	}
	
	public int getTotalPlayers() {
		return connections.size();
	}
}
