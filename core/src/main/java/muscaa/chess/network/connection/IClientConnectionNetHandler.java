package muscaa.chess.network.connection;

import muscaa.chess.network.connection.packets.CPacketHandshake;
import muscaa.chess.shared.network.connection.IConnectionNetHandler;

public interface IClientConnectionNetHandler extends IConnectionNetHandler {
	
	void onPacketHandshake(CPacketHandshake packet);
}
