package muscaa.chess.network.connection;

import muscaa.chess.network.connection.packets.PacketEncrypt;

public interface IServerConnectionNetHandler extends IConnectionNetHandler {
	
	void onPacketEncrypt(PacketEncrypt packet);
}
