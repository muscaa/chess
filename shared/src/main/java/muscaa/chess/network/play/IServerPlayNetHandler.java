package muscaa.chess.network.play;

import muscaa.chess.network.common.IServerCommonNetHandler;
import muscaa.chess.network.play.packets.SPacketClickCell;

public interface IServerPlayNetHandler extends IServerCommonNetHandler, IPlayNetHandler {
	
	void onPacketClickCell(SPacketClickCell packet);
}
