package muscaa.chess.client.network.connection;

import muscaa.chess.client.network.common.IClientCommonNetHandler;
import muscaa.chess.client.network.connection.packets.CPacketHandshake;
import muscaa.chess.network.connection.IConnectionNetHandler;

public interface IClientConnectionNetHandler extends IClientCommonNetHandler, IConnectionNetHandler {
	
	void onPacketHandshake(CPacketHandshake packet);
}
