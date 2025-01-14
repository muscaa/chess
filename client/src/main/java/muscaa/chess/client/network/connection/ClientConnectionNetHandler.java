package muscaa.chess.client.network.connection;

import fluff.network.packet.channels.EncryptedPacketChannel;
import muscaa.chess.client.network.ClientContextRegistry;
import muscaa.chess.client.network.common.ClientCommonNetHandler;
import muscaa.chess.client.network.connection.packets.CPacketHandshake;

public class ClientConnectionNetHandler extends ClientCommonNetHandler implements IClientConnectionNetHandler {
	
	@Override
	public void onPacketHandshake(CPacketHandshake packet) {
		client.setChannel(new EncryptedPacketChannel(packet.getKey()));
		client.setContext(ClientContextRegistry.LOGIN.get());
	}
}
