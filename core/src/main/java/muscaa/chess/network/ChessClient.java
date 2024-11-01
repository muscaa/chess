package muscaa.chess.network;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import fluff.network.NetworkException;
import fluff.network.client.AbstractClient;

public class ChessClient extends AbstractClient {
	
    @SuppressWarnings("resource")
	public void connect(String host, int port) throws UnknownHostException, IOException, NetworkException {
        openConnection(new Socket(host, port));
    }
}
