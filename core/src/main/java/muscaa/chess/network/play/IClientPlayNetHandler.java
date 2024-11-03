package muscaa.chess.network.play;

import muscaa.chess.network.play.packets.PacketEndGame;
import muscaa.chess.network.play.packets.PacketMove;
import muscaa.chess.network.play.packets.PacketStartGame;
import muscaa.chess.shared.network.play.IPlayNetHandler;

public interface IClientPlayNetHandler extends IPlayNetHandler {
	
	void onPacketStartGame(PacketStartGame packet);
	
	void onPacketEndGame(PacketEndGame packet);
	
	void onPacketMove(PacketMove packet);
}
