package muscaa.chess.network.connection;

import fluff.network.packet.channels.EncryptedPacketChannel;
import muscaa.chess.Chess;
import muscaa.chess.network.DisconnectReasonRegistry;
import muscaa.chess.network.ServerContextRegistry;
import muscaa.chess.network.base.ServerBaseNetHandler;
import muscaa.chess.network.connection.packets.SPacketEncrypt;
import muscaa.chess.network.connection.packets.SPacketHandshake;

public class ServerConnectionNetHandler extends ServerBaseNetHandler implements IServerConnectionNetHandler {
	
	@Override
	public void onPacketEncrypt(SPacketEncrypt packet) {
		if (packet.getProtocolVersion() != Chess.PROTOCOL_VERSION) {
			connection.disconnect(DisconnectReasonRegistry.KICK.get(), "Invalid protocol version!");
            return;
		}
		
		connection.send(new SPacketHandshake(packet.getKey()));
		connection.setChannel(new EncryptedPacketChannel(packet.getKey()));
		connection.setContext(ServerContextRegistry.LOGIN.get());
	}
}
