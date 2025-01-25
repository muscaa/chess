package muscaa.chess.network.ping;

import fluff.network.client.IClient;
import muscaa.chess.AbstractServer;
import muscaa.chess.network.base.ServerBaseNetHandler;
import muscaa.chess.network.ping.packets.SPacketPing;

public class ServerPingNetHandler extends ServerBaseNetHandler implements IServerPingNetHandler {
	
	protected AbstractServer gameServer;
	
	public ServerPingNetHandler(AbstractServer gameServer) {
		this.gameServer = gameServer;
	}
	
	@Override
	public void onInit(IClient client) {
		super.onInit(client);
		
		
		connection.send(new SPacketPing(gameServer.getPlayerCount(), gameServer.getMaxPlayerCount(), gameServer.getMotd()));
		connection.disconnect();
	}
}
