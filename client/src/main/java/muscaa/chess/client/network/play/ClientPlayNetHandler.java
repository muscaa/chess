package muscaa.chess.client.network.play;

import muscaa.chess.client.Client;
import muscaa.chess.client.network.common.ClientCommonNetHandler;
import muscaa.chess.client.network.play.packets.CPacketBoard;
import muscaa.chess.client.network.play.packets.CPacketEndGame;
import muscaa.chess.client.network.play.packets.CPacketHighlightCells;
import muscaa.chess.client.network.play.packets.CPacketStartGame;
import muscaa.chess.client.network.play.packets.CPacketTeam;

public class ClientPlayNetHandler extends ClientCommonNetHandler implements IClientPlayNetHandler {
	
	@Override
	public void onPacketStartGame(CPacketStartGame packet) {
		Client.INSTANCE.getBoardLayer().onPacketStartGame(packet);
	}
	
	@Override
	public void onPacketBoard(CPacketBoard packet) {
		Client.INSTANCE.getBoardLayer().onPacketBoard(packet);
	}
	
	@Override
	public void onPacketTeam(CPacketTeam packet) {
		Client.INSTANCE.getBoardLayer().onPacketTeam(packet);
	}
	
	@Override
	public void onPacketHighlightCells(CPacketHighlightCells packet) {
		Client.INSTANCE.getBoardLayer().onPacketHighlightCells(packet);
	}
	
	@Override
	public void onPacketEndGame(CPacketEndGame packet) {
		Client.INSTANCE.getBoardLayer().onPacketEndGame(packet);
	}
}
