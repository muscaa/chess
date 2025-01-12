package muscaa.chess.network.play;

import muscaa.chess.network.play.packets.SPacketClickCell;

public interface IServerPlayNetHandler extends IPlayNetHandler {
	
	void onPacketClickCell(SPacketClickCell packet);
}
