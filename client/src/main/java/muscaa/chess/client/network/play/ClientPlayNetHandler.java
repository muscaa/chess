package muscaa.chess.client.network.play;

import fluff.network.client.IClient;
import muscaa.chess.client.Client;
import muscaa.chess.client.network.NetworkStatus;
import muscaa.chess.client.network.common.ClientCommonNetHandler;
import muscaa.chess.client.network.play.packets.CPacketBoard;
import muscaa.chess.client.network.play.packets.CPacketGameEnd;
import muscaa.chess.client.network.play.packets.CPacketHighlightCells;
import muscaa.chess.client.network.play.packets.CPacketGameStart;
import muscaa.chess.client.network.play.packets.CPacketTeam;

public class ClientPlayNetHandler extends ClientCommonNetHandler implements IClientPlayNetHandler {
	
	@Override
	public void onInit(IClient client) {
		super.onInit(client);
		
		this.client.update(NetworkStatus.DONE);
	}
	
	@Override
	public void onPacketStartGame(CPacketGameStart packet) {
		Client.INSTANCE.getBoard().startGame();
	}
	
	@Override
	public void onPacketBoard(CPacketBoard packet) {
		Client.INSTANCE.getBoard().updateBoard(packet.getWidth(), packet.getHeight(), packet.getPieces());
	}
	
	@Override
	public void onPacketTeam(CPacketTeam packet) {
		Client.INSTANCE.getBoard().setTeam(packet.getTeam());
	}
	
	@Override
	public void onPacketHighlightCells(CPacketHighlightCells packet) {
		Client.INSTANCE.getBoard().setHighlights(packet.getHighlights());
	}
	
	@Override
	public void onPacketEndGame(CPacketGameEnd packet) {
		Client.INSTANCE.getBoard().endGame(packet.getWinner());
	}
}
