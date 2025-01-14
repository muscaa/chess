package muscaa.chess.network;

import fluff.network.NetworkException;
import fluff.network.packet.channels.DefaultPacketChannel;
import fluff.network.server.AbstractClientConnection;
import fluff.network.server.AbstractServer;
import fluff.network.server.modules.TimeoutModule;
import muscaa.chess.network.common.packets.PacketDisconnect;

public class ChessServer extends AbstractServer {
	
	public ChessServer(int port) {
		super(port);
		
		addModule(new TimeoutModule(30000));
		
		//setDefaultContext(ServerContexts.INTENT_CONTEXT, ServerIntentNetHandler::new);
		setDefaultContext(ServerContextRegistry.INTENT.get().getContext(), ServerContextRegistry.INTENT.get().getHandlerFunc());
		setDefaultChannel(DefaultPacketChannel::new);
	}
	
	@Override
	protected AbstractClientConnection createConnection() {
		return new ChessClientConnection(this, defaultContext, defaultHandlerFunc.invoke(), defaultChannelFunc.invoke());
	}
	
	@Override
	protected void onConnect(AbstractClientConnection connection) throws NetworkException {
		super.onConnect(connection);
	}
	
	@Override
	protected void onDisconnect(AbstractClientConnection connection) {
		super.onDisconnect(connection);
	}
	
	@Override
	public void disconnectAll() {
		sendAll(new PacketDisconnect("Server stopped!"));
		super.disconnectAll();
	}
	
	public int getTotalPlayers() {
		return connections.size();
	}
}
