package muscaa.chess.shared.network.common;

import fluff.network.INetHandler;
import muscaa.chess.shared.network.common.packets.PacketDisconnect;

public interface ICommonNetHandler extends INetHandler {
	
	void onPacketDisconnect(PacketDisconnect packet);
}
