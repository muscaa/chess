package muscaa.chess.server.network.play;

import muscaa.chess.server.network.play.packet.PacketClickCell;
import muscaa.chess.shared.network.play.IPlayNetHandler;

public interface IServerPlayNetHandler extends IPlayNetHandler {
	
	void onPacketClickCell(PacketClickCell packet);
}
