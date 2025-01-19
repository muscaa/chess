package muscaa.chess.client.network.ping;

import muscaa.chess.client.network.PingChessClient;
import muscaa.chess.client.network.common.ClientCommonNetHandler;
import muscaa.chess.client.network.ping.packets.CPacketPing;

public class ClientPingNetHandler extends ClientCommonNetHandler implements IClientPingNetHandler {
	
	@Override
	public void onPacketPing(CPacketPing packet) {
		if (client instanceof PingChessClient pingClient) {
			pingClient.pingFuture.complete(packet);
		}
		
		client.disconnect();
	}
}
