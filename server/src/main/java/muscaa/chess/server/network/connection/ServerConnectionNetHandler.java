package muscaa.chess.server.network.connection;

import fluff.network.AbstractServerNetHandler;
import fluff.network.packet.channels.EncryptedPacketChannel;
import muscaa.chess.server.GameServer;
import muscaa.chess.server.network.ChessClientConnection;
import muscaa.chess.server.network.ChessServer;
import muscaa.chess.server.network.ServerContexts;
import muscaa.chess.server.network.connection.packets.PacketEncrypt;
import muscaa.chess.server.network.connection.packets.PacketHandshake;
import muscaa.chess.server.network.login.ServerLoginNetHandler;

public class ServerConnectionNetHandler extends AbstractServerNetHandler<ChessServer, ChessClientConnection> implements IServerConnectionNetHandler {
	
	@Override
	public void onPacketEncrypt(PacketEncrypt packet) {
		System.out.println("Received encrypt");
		
		if (GameServer.INSTANCE.getBoard().players.size() == 2) {
			connection.disconnect();
			return;
		}
		
		connection.send(new PacketHandshake(packet.getKey()));
		connection.setChannel(new EncryptedPacketChannel(packet.getKey()));
		connection.setContext(ServerContexts.LOGIN_CONTEXT, new ServerLoginNetHandler());
	}
}
