package muscaa.chess.shared.network.connection;

import fluff.network.packet.channels.EncryptedPacketChannel;
import muscaa.chess.shared.network.ServerContexts;
import muscaa.chess.shared.network.common.ServerCommonNetHandler;
import muscaa.chess.shared.network.connection.packets.PacketEncrypt;
import muscaa.chess.shared.network.connection.packets.PacketHandshake;
import muscaa.chess.shared.network.login.ServerLoginNetHandler;

public class ServerConnectionNetHandler extends ServerCommonNetHandler implements IServerConnectionNetHandler {
	
	@Override
	public void onPacketEncrypt(PacketEncrypt packet) {
		//System.out.println("Received encrypt");
		
		// TODO allow multiple people on the server using lobbies
		if (server.getTotalPlayers() >= 2) {
			connection.disconnect("Server is full!");
			return;
		}
		
		connection.send(new PacketHandshake(packet.getKey()));
		connection.setChannel(new EncryptedPacketChannel(packet.getKey()));
		connection.setContext(ServerContexts.LOGIN_CONTEXT, new ServerLoginNetHandler());
	}
}
