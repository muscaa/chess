package muscaa.chess.network.connection;

import fluff.network.packet.channels.EncryptedPacketChannel;
import muscaa.chess.Chess;
import muscaa.chess.network.ServerContexts;
import muscaa.chess.network.common.ServerCommonNetHandler;
import muscaa.chess.network.connection.packets.PacketEncrypt;
import muscaa.chess.network.connection.packets.PacketHandshake;
import muscaa.chess.network.login.ServerLoginNetHandler;

public class ServerConnectionNetHandler extends ServerCommonNetHandler implements IServerConnectionNetHandler {
	
	@Override
	public void onPacketEncrypt(PacketEncrypt packet) {
		if (packet.getProtocolVersion() != Chess.PROTOCOL_VERSION) {
			connection.disconnect("Invalid protocol version!");
            return;
		}
		
		System.out.println("Received encrypt");
		
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
