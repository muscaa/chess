package muscaa.chess.server.network;

import muscaa.chess.AbstractServer;
import muscaa.chess.network.login.ServerLoginNetHandler;

public class DedicatedServerLoginNetHandler extends ServerLoginNetHandler {
	
	public DedicatedServerLoginNetHandler(AbstractServer gameServer) {
		super(gameServer);
	}
}
