package muscaa.chess.shared;

import fluff.network.INetHandler;

public interface IMessageNetHandler extends INetHandler {
	
	void onPacketMessage(PacketMessage packet);
}
