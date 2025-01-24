package muscaa.chess.client.network.play;

import fluff.network.client.IClient;
import muscaa.chess.client.Client;
import muscaa.chess.client.network.ConnectChessClient;
import muscaa.chess.client.network.NetworkStatus;
import muscaa.chess.client.network.common.ClientCommonNetHandler;
import muscaa.chess.client.network.play.packets.CPacketBoard;
import muscaa.chess.client.network.play.packets.CPacketGameEnd;
import muscaa.chess.client.network.play.packets.CPacketGameStart;
import muscaa.chess.client.network.play.packets.CPacketHighlightCells;
import muscaa.chess.client.network.play.packets.CPacketTeam;
import muscaa.chess.client.network.play.packets.CPacketTurn;

public class ClientPlayNetHandler extends ClientCommonNetHandler implements IClientPlayNetHandler {
	
	@Override
	public void onInit(IClient client) {
		super.onInit(client);
		
		if (client instanceof ConnectChessClient connectClient) {
			connectClient.update(NetworkStatus.DONE);
		}
	}
	
	@Override
	public void onPacketStartGame(CPacketGameStart packet) {
		Client.INSTANCE.getPlayer().getBoard().onStartGame();
	}
	
	@Override
	public void onPacketBoard(CPacketBoard packet) {
		Client.INSTANCE.getPlayer().getBoard().onUpdateBoard(packet.getWidth(), packet.getHeight(), packet.getPieces());
	}
	
	@Override
	public void onPacketTeam(CPacketTeam packet) {
		Client.INSTANCE.getPlayer().getBoard().setTeam(packet.getTeam());
	}
	
	@Override
	public void onPacketTurn(CPacketTurn packet) {
		Client.INSTANCE.getPlayer().getBoard().onUpdateTurn(packet.getTurn());
	}
	
	@Override
	public void onPacketHighlightCells(CPacketHighlightCells packet) {
		Client.INSTANCE.getPlayer().getBoard().setHighlights(packet.getHighlights());
	}
	
	@Override
	public void onPacketEndGame(CPacketGameEnd packet) {
		Client.INSTANCE.getPlayer().getBoard().onEndGame(packet.getWinner());
	}
}
