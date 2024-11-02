package muscaa.chess.server;

import java.util.Scanner;

import fluff.network.packet.channels.DefaultPacketChannel;
import muscaa.chess.server.network.ChessServer;
import muscaa.chess.server.network.NetworkServer;
import muscaa.chess.server.network.connection.ServerConnectionNetHandler;

public class ServerLauncher {
	
    public static void main(String[] args) throws Exception {
		ChessServer server = new ChessServer(40755);
		server.setDefaultContext(NetworkServer.CONNECTION_CONTEXT, ServerConnectionNetHandler::new);
		server.setDefaultChannel(DefaultPacketChannel::new);
		server.start(true);
		
		System.out.println("Server started. Type 'stop' to stop.");
		Scanner s = new Scanner(System.in);
		while (s.hasNextLine()) {
			String line = s.nextLine();
			if (line.equals("stop")) break;
		}
		s.close();
		
		server.stop();
    }
}