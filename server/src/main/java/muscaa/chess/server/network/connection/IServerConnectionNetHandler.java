package muscaa.chess.server.network.connection;

import muscaa.chess.server.network.connection.packets.PacketEncrypt;
import muscaa.chess.shared.network.connection.IConnectionNetHandler;

public interface IServerConnectionNetHandler extends IConnectionNetHandler {
	
	void onPacketEncrypt(PacketEncrypt packet);
}
