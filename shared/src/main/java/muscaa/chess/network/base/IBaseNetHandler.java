package muscaa.chess.network.base;

import fluff.network.INetHandler;
import muscaa.chess.network.base.packets.PacketDisconnect;

public interface IBaseNetHandler extends INetHandler {
	
	void onPacketDisconnect(PacketDisconnect packet);
}
