package muscaa.chess.network.common;

import muscaa.chess.AbstractServer;
import muscaa.chess.network.base.ServerBaseNetHandler;
import muscaa.chess.network.common.packets.SPacketChatMessage;

public abstract class ServerCommonNetHandler extends ServerBaseNetHandler implements IServerCommonNetHandler {
	
	protected final AbstractServer gameServer;
	
	public ServerCommonNetHandler(AbstractServer server) {
		this.gameServer = server;
	}
	
	@Override
	public void onPacketChatMessage(SPacketChatMessage packet) {
		connection.player.onSendChatMessage(packet.getMessage());
	}
}
