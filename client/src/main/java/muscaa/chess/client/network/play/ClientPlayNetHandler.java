package muscaa.chess.client.network.play;

import muscaa.chess.client.Client;
import muscaa.chess.client.network.common.ClientCommonNetHandler;
import muscaa.chess.client.network.play.packets.CPacketBoard;
import muscaa.chess.client.network.play.packets.CPacketGameEnd;
import muscaa.chess.client.network.play.packets.CPacketHighlightCells;
import muscaa.chess.client.network.play.packets.CPacketGameStart;
import muscaa.chess.client.network.play.packets.CPacketTeam;

public class ClientPlayNetHandler extends ClientCommonNetHandler implements IClientPlayNetHandler {
	
	@Override
	public void onPacketStartGame(CPacketGameStart packet) {
		Client.INSTANCE.getBoard().onPacketStartGame(packet);
	}
	
	@Override
	public void onPacketBoard(CPacketBoard packet) {
		Client.INSTANCE.getBoard().onPacketBoard(packet);
	}
	
	@Override
	public void onPacketTeam(CPacketTeam packet) {
		Client.INSTANCE.getBoard().onPacketTeam(packet);
	}
	
	@Override
	public void onPacketHighlightCells(CPacketHighlightCells packet) {
		Client.INSTANCE.getBoard().onPacketHighlightCells(packet);
	}
	
	@Override
	public void onPacketEndGame(CPacketGameEnd packet) {
		Client.INSTANCE.getBoard().onPacketEndGame(packet);
	}
}
