package muscaa.chess.network.connection;

import muscaa.chess.network.connection.packets.PacketHandshake;
import muscaa.chess.shared.network.connection.IConnectionNetHandler;

public interface IClientConnectionNetHandler extends IConnectionNetHandler {
	
	void onPacketHandshake(PacketHandshake packet);
}
