package muscaa.chess.shared.network.connection;

import muscaa.chess.shared.network.connection.packets.PacketEncrypt;

public interface IServerConnectionNetHandler extends IConnectionNetHandler {
	
	void onPacketEncrypt(PacketEncrypt packet);
}
