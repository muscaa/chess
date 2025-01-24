package muscaa.chess.client.network.common;

import muscaa.chess.client.network.base.IClientBaseNetHandler;
import muscaa.chess.client.network.common.packets.CPacketChatLine;
import muscaa.chess.client.network.common.packets.CPacketJoinBoard;
import muscaa.chess.client.network.common.packets.CPacketLeaveBoard;
import muscaa.chess.network.common.ICommonNetHandler;

public interface IClientCommonNetHandler extends IClientBaseNetHandler, ICommonNetHandler {
	
	void onPacketChatLine(CPacketChatLine packet);
	
	void onPacketJoinBoard(CPacketJoinBoard packet);
	
	void onPacketLeaveBoard(CPacketLeaveBoard packet);
}
