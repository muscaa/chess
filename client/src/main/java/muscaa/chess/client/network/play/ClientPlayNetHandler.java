package muscaa.chess.client.network.play;

import muscaa.chess.client.Core;
import muscaa.chess.client.network.common.ClientCommonNetHandler;
import muscaa.chess.client.network.play.packets.CPacketBoard;
import muscaa.chess.client.network.play.packets.CPacketEndGame;
import muscaa.chess.client.network.play.packets.CPacketHighlightCells;
import muscaa.chess.client.network.play.packets.CPacketStartGame;
import muscaa.chess.client.network.play.packets.CPacketTeam;

public class ClientPlayNetHandler extends ClientCommonNetHandler implements IClientPlayNetHandler {
	
	@Override
	public void onPacketStartGame(CPacketStartGame packet) {
		Core.INSTANCE.getBoardLayer().onPacketStartGame(packet);
	}
	
	@Override
	public void onPacketBoard(CPacketBoard packet) {
		Core.INSTANCE.getBoardLayer().onPacketBoard(packet);
	}
	
	@Override
	public void onPacketTeam(CPacketTeam packet) {
		Core.INSTANCE.getBoardLayer().onPacketTeam(packet);
	}
	
	@Override
	public void onPacketHighlightCells(CPacketHighlightCells packet) {
		Core.INSTANCE.getBoardLayer().onPacketHighlightCells(packet);
	}
	
	@Override
	public void onPacketEndGame(CPacketEndGame packet) {
		Core.INSTANCE.getBoardLayer().onPacketEndGame(packet);
	}
}
