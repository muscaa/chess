package muscaa.chess.client.network.play;

import muscaa.chess.client.network.common.IClientCommonNetHandler;
import muscaa.chess.client.network.play.packets.CPacketBoard;
import muscaa.chess.client.network.play.packets.CPacketGameEnd;
import muscaa.chess.client.network.play.packets.CPacketGameStart;
import muscaa.chess.client.network.play.packets.CPacketHighlightCells;
import muscaa.chess.client.network.play.packets.CPacketTeam;
import muscaa.chess.client.network.play.packets.CPacketTurn;
import muscaa.chess.network.play.IPlayNetHandler;

public interface IClientPlayNetHandler extends IClientCommonNetHandler, IPlayNetHandler {
	
	void onPacketStartGame(CPacketGameStart packet);
	
	void onPacketBoard(CPacketBoard packet);
	
	void onPacketTeam(CPacketTeam packet);
	
	void onPacketTurn(CPacketTurn packet);
	
	void onPacketHighlightCells(CPacketHighlightCells packet);
	
	void onPacketEndGame(CPacketGameEnd packet);
}
