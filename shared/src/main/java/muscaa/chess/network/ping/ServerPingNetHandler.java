package muscaa.chess.network.ping;

import fluff.network.client.IClient;
import muscaa.chess.chat.ChatUtils;
import muscaa.chess.network.base.ServerBaseNetHandler;
import muscaa.chess.network.ping.packets.SPacketPing;

public class ServerPingNetHandler extends ServerBaseNetHandler implements IServerPingNetHandler {
	
	@Override
	public void onInit(IClient client) {
		super.onInit(client);
		
		connection.send(new SPacketPing(1, 2, ChatUtils.format("&7hello"), ChatUtils.format("&6kit&4ty")));
		connection.disconnect();
	}
}
