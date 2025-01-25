package muscaa.chess.network.base;

import muscaa.chess.network.IServerNetHandler;
import muscaa.chess.network.base.packets.SPacketFormData;

public interface IServerBaseNetHandler extends IServerNetHandler, IBaseNetHandler {
	
	void onPacketFormData(SPacketFormData packet);
}
