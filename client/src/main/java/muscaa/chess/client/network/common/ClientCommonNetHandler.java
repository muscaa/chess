package muscaa.chess.client.network.common;

import muscaa.chess.client.Client;
import muscaa.chess.client.board.boards.RemoteClientBoard;
import muscaa.chess.client.network.base.ClientBaseNetHandler;
import muscaa.chess.client.network.common.packets.CPacketChatLine;
import muscaa.chess.client.network.common.packets.CPacketJoinBoard;
import muscaa.chess.client.network.common.packets.CPacketLeaveBoard;

public abstract class ClientCommonNetHandler extends ClientBaseNetHandler implements IClientCommonNetHandler {
	
	@Override
	public void onPacketChatLine(CPacketChatLine packet) {
		Client.INSTANCE.getPlayer().onAddChatLine(packet.getLine());
	}
	
	@Override
	public void onPacketJoinBoard(CPacketJoinBoard packet) {
		RemoteClientBoard board = new RemoteClientBoard(client);
		
		Client.INSTANCE.getPlayer().setBoard(board);
	}
	
	@Override
	public void onPacketLeaveBoard(CPacketLeaveBoard packet) {
		Client.INSTANCE.getPlayer().setBoard(null);
	}
}
