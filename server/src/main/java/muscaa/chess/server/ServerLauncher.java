package muscaa.chess.server;

import java.util.Scanner;

import fluff.network.packet.PacketContext;
import fluff.network.packet.channels.DefaultPacketChannel;
import muscaa.chess.shared.PacketMessage;

public class ServerLauncher {
	
	public static final PacketContext<IServerNetHandler> SERVER_CONTEXT = new PacketContext<IServerNetHandler>("server_context")
			.extend(PacketMessage.COMMON_CONTEXT)
			;
	
    public static void main(String[] args) throws Exception {
		ChessServer server = new ChessServer(40755);
		server.setDefaultContext(SERVER_CONTEXT, ServerNetHandler::new);
		server.setDefaultChannel(DefaultPacketChannel::new);
		server.start(true);
		
		Scanner s = new Scanner(System.in);
		while (s.hasNextLine()) {
			String line = s.nextLine();
			if (line.equals("stop")) break;
			
			server.sendAll(new PacketMessage(line));
		}
		s.close();
		
		server.stop();
    }
}