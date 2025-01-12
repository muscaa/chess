package muscaa.chess.network.play;

import fluff.network.client.IClient;
import muscaa.chess.Server;
import muscaa.chess.network.common.ServerCommonNetHandler;
import muscaa.chess.network.play.packets.SPacketClickCell;

public class ServerPlayNetHandler extends ServerCommonNetHandler implements IServerPlayNetHandler {
	
	@Override
	public void onInit(IClient client) {
		super.onInit(client);
		
		Server.INSTANCE.getBoard().onConnect(connection);
	}
	
	@Override
	public void onDisconnect() {
		Server.INSTANCE.getBoard().onDisconnect(connection);
	}
	
	@Override
	public void onPacketClickCell(SPacketClickCell packet) {
		Server.INSTANCE.getBoard().onPacketClickCell(connection, packet);
	}
}
