package muscaa.chess.network;

import fluff.network.NetworkException;
import fluff.network.packet.channels.DefaultPacketChannel;
import muscaa.chess.network.connection.ServerConnectionNetHandler;

public class ServerNetwork {
	
	private final ChessServer server;
	
	public ServerNetwork() {
		server = new ChessServer(40755);
		server.setDefaultContext(ServerContexts.CONNECTION_CONTEXT, ServerConnectionNetHandler::new);
		server.setDefaultChannel(DefaultPacketChannel::new);
	}
	
	public void start() throws NetworkException {
		server.start(true);
	}
	
	public void stop() {
		server.stop();
	}
	
	public ChessServer getServer() {
		return server;
	}
}
