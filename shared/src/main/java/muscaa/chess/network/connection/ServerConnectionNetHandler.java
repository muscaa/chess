package muscaa.chess.network.connection;

import fluff.network.packet.channels.EncryptedPacketChannel;
import muscaa.chess.Chess;
import muscaa.chess.network.ServerContexts;
import muscaa.chess.network.common.ServerCommonNetHandler;
import muscaa.chess.network.connection.packets.SPacketEncrypt;
import muscaa.chess.network.connection.packets.SPacketHandshake;
import muscaa.chess.network.login.ServerLoginNetHandler;

public class ServerConnectionNetHandler extends ServerCommonNetHandler implements IServerConnectionNetHandler {
	
	@Override
	public void onPacketEncrypt(SPacketEncrypt packet) {
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
		
		connection.send(new SPacketHandshake(packet.getKey()));
		connection.setChannel(new EncryptedPacketChannel(packet.getKey()));
		connection.setContext(ServerContexts.LOGIN_CONTEXT, new ServerLoginNetHandler());
	}
}
