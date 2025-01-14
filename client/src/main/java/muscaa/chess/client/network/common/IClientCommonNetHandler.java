package muscaa.chess.client.network.common;

import muscaa.chess.client.network.IClientNetHandler;
import muscaa.chess.client.network.common.packets.CPacketChangeContext;
import muscaa.chess.network.common.ICommonNetHandler;

public interface IClientCommonNetHandler extends IClientNetHandler, ICommonNetHandler {
	
	void onPacketChangeContext(CPacketChangeContext packet);
}
