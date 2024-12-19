package muscaa.chess.network.play;

import muscaa.chess.network.play.packets.CPacketBoard;
import muscaa.chess.network.play.packets.CPacketEndGame;
import muscaa.chess.network.play.packets.CPacketHighlightCells;
import muscaa.chess.network.play.packets.CPacketStartGame;
import muscaa.chess.network.play.packets.CPacketTeam;
import muscaa.chess.shared.network.play.IPlayNetHandler;

public interface IClientPlayNetHandler extends IPlayNetHandler {
	
	void onPacketStartGame(CPacketStartGame packet);
	
	void onPacketBoard(CPacketBoard packet);
	
	void onPacketTeam(CPacketTeam packet);
	
	void onPacketHighlightCells(CPacketHighlightCells packet);
	
	void onPacketEndGame(CPacketEndGame packet);
}
