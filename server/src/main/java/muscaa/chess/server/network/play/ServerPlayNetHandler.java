package muscaa.chess.server.network.play;

import fluff.network.client.IClient;
import muscaa.chess.server.GameServer;
import muscaa.chess.server.network.common.ServerCommonNetHandler;
import muscaa.chess.server.network.play.packet.PacketClickCell;

public class ServerPlayNetHandler extends ServerCommonNetHandler implements IServerPlayNetHandler {
	
	@Override
	public void onInit(IClient client) {
		super.onInit(client);
		
		GameServer.INSTANCE.getBoard().onConnect(connection);
	}
	
	@Override
	public void onDisconnect() {
		GameServer.INSTANCE.getBoard().onDisconnect(connection);
	}
	
	@Override
	public void onPacketClickCell(PacketClickCell packet) {
		GameServer.INSTANCE.getBoard().onPacketClickCell(connection, packet);
	}
}
