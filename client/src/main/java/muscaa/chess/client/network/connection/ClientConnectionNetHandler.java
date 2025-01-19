package muscaa.chess.client.network.connection;

import fluff.network.client.IClient;
import fluff.network.packet.channels.EncryptedPacketChannel;
import muscaa.chess.Chess;
import muscaa.chess.client.network.ConnectChessClient;
import muscaa.chess.client.network.NetworkStatus;
import muscaa.chess.client.network.common.ClientCommonNetHandler;
import muscaa.chess.client.network.connection.packets.CPacketEncrypt;
import muscaa.chess.client.network.connection.packets.CPacketHandshake;

public class ClientConnectionNetHandler extends ClientCommonNetHandler implements IClientConnectionNetHandler {
	
	protected void sendEncrypt() {
		client.send(new CPacketEncrypt(Chess.PROTOCOL_VERSION, CPacketEncrypt.generate()));
		
		if (client instanceof ConnectChessClient connectClient) {
			connectClient.update(NetworkStatus.ENCRYPT);
		}
	}
	
	@Override
	public void onInit(IClient client) {
		super.onInit(client);
		
		sendEncrypt();
	}
	
	@Override
	public void onPacketHandshake(CPacketHandshake packet) {
		client.setChannel(new EncryptedPacketChannel(packet.getKey()));
	}
}
