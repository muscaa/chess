package muscaa.chess.network.play;

import muscaa.chess.network.play.packets.PacketBoard;
import muscaa.chess.shared.network.play.IPlayNetHandler;

public interface IClientPlayNetHandler extends IPlayNetHandler {
	
	void onPacketBoard(PacketBoard packet);
}
