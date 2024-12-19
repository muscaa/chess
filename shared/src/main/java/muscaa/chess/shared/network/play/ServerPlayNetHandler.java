package muscaa.chess.shared.network.play;

import fluff.network.client.IClient;
import muscaa.chess.shared.Server;
import muscaa.chess.shared.network.common.ServerCommonNetHandler;
import muscaa.chess.shared.network.play.packets.PacketClickCell;

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
	public void onPacketClickCell(PacketClickCell packet) {
		Server.INSTANCE.getBoard().onPacketClickCell(connection, packet);
	}
}
