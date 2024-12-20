package muscaa.chess.network.common;

import fluff.network.INetHandler;
import muscaa.chess.network.common.packets.PacketDisconnect;

public interface ICommonNetHandler extends INetHandler {
	
	void onPacketDisconnect(PacketDisconnect packet);
}
