package muscaa.chess.shared.network.play;

import muscaa.chess.shared.network.play.packets.PacketClickCell;

public interface IServerPlayNetHandler extends IPlayNetHandler {
	
	void onPacketClickCell(PacketClickCell packet);
}
