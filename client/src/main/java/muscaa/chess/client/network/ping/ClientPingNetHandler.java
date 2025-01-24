package muscaa.chess.client.network.ping;

import muscaa.chess.client.network.PingChessClient;
import muscaa.chess.client.network.base.ClientBaseNetHandler;
import muscaa.chess.client.network.ping.packets.CPacketPing;

public class ClientPingNetHandler extends ClientBaseNetHandler implements IClientPingNetHandler {
	
	@Override
	public void onPacketPing(CPacketPing packet) {
		if (client instanceof PingChessClient pingClient) {
			pingClient.pingFuture.complete(packet);
		}
		
		client.disconnect();
	}
}
