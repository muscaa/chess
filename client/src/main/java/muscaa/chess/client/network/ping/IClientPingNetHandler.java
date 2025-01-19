package muscaa.chess.client.network.ping;

import muscaa.chess.client.network.common.IClientCommonNetHandler;
import muscaa.chess.client.network.ping.packets.CPacketPing;
import muscaa.chess.network.ping.IPingNetHandler;

public interface IClientPingNetHandler extends IClientCommonNetHandler, IPingNetHandler {
	
	void onPacketPing(CPacketPing packet);
}
