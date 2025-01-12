package muscaa.chess.client.board;

import java.io.IOException;
import java.net.UnknownHostException;

import fluff.network.NetworkException;
import muscaa.chess.client.config.ServersConfig.Server;

public class OnlineBoard extends AbstractBoard {
	
	public OnlineBoard(Server server) throws UnknownHostException, IOException, NetworkException {
		super(server);
	}
}
