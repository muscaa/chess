package muscaa.chess.network.play;

import muscaa.chess.Core;
import muscaa.chess.network.common.ClientCommonNetHandler;
import muscaa.chess.network.play.packets.CPacketBoard;
import muscaa.chess.network.play.packets.CPacketEndGame;
import muscaa.chess.network.play.packets.CPacketHighlightCells;
import muscaa.chess.network.play.packets.CPacketStartGame;
import muscaa.chess.network.play.packets.CPacketTeam;

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
