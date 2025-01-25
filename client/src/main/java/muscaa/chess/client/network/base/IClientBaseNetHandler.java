package muscaa.chess.client.network.base;

import muscaa.chess.client.network.IClientNetHandler;
import muscaa.chess.client.network.base.packets.CPacketChangeContext;
import muscaa.chess.client.network.base.packets.CPacketForm;
import muscaa.chess.network.base.IBaseNetHandler;

public interface IClientBaseNetHandler extends IClientNetHandler, IBaseNetHandler {
	
	void onPacketChangeContext(CPacketChangeContext packet);
	
	void onPacketForm(CPacketForm packet);
}
