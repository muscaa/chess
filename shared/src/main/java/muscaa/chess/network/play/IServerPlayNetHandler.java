package muscaa.chess.network.play;

import muscaa.chess.network.play.packets.PacketClickCell;

public interface IServerPlayNetHandler extends IPlayNetHandler {
	
	void onPacketClickCell(PacketClickCell packet);
}
