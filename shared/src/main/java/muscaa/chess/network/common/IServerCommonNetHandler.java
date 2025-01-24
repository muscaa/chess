package muscaa.chess.network.common;

import muscaa.chess.network.base.IServerBaseNetHandler;
import muscaa.chess.network.common.packets.SPacketChatMessage;

public interface IServerCommonNetHandler extends IServerBaseNetHandler, ICommonNetHandler {
	
	void onPacketChatMessage(SPacketChatMessage packet);
}
