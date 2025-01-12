package muscaa.chess.network.connection;

import muscaa.chess.network.connection.packets.SPacketEncrypt;

public interface IServerConnectionNetHandler extends IConnectionNetHandler {
	
	void onPacketEncrypt(SPacketEncrypt packet);
}
