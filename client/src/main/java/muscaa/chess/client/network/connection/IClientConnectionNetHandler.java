package muscaa.chess.client.network.connection;

import muscaa.chess.client.network.base.IClientBaseNetHandler;
import muscaa.chess.client.network.connection.packets.CPacketHandshake;
import muscaa.chess.network.connection.IConnectionNetHandler;

public interface IClientConnectionNetHandler extends IClientBaseNetHandler, IConnectionNetHandler {
	
	void onPacketHandshake(CPacketHandshake packet);
}
