package muscaa.chess.client.network.play;

import muscaa.chess.client.network.play.packets.CPacketBoard;
import muscaa.chess.client.network.play.packets.CPacketEndGame;
import muscaa.chess.client.network.play.packets.CPacketHighlightCells;
import muscaa.chess.client.network.play.packets.CPacketStartGame;
import muscaa.chess.client.network.play.packets.CPacketTeam;
import muscaa.chess.network.play.IPlayNetHandler;

public interface IClientPlayNetHandler extends IPlayNetHandler {
	
	void onPacketStartGame(CPacketStartGame packet);
	
	void onPacketBoard(CPacketBoard packet);
	
	void onPacketTeam(CPacketTeam packet);
	
	void onPacketHighlightCells(CPacketHighlightCells packet);
	
	void onPacketEndGame(CPacketEndGame packet);
}
