package muscaa.chess.client.network.ping;

import muscaa.chess.client.network.base.IClientBaseNetHandler;
import muscaa.chess.client.network.ping.packets.CPacketPing;
import muscaa.chess.network.ping.IPingNetHandler;

public interface IClientPingNetHandler extends IClientBaseNetHandler, IPingNetHandler {
	
	void onPacketPing(CPacketPing packet);
}
