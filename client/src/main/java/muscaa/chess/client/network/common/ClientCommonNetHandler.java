package muscaa.chess.client.network.common;

import fluff.network.AbstractClientNetHandler;
import fluff.network.NetworkException;
import muscaa.chess.client.network.AbstractChessClient;
import muscaa.chess.client.network.ConnectChessClient;
import muscaa.chess.client.network.common.packets.CPacketChangeContext;
import muscaa.chess.network.common.packets.PacketDisconnect;

public class ClientCommonNetHandler extends AbstractClientNetHandler<AbstractChessClient> implements IClientCommonNetHandler {
	
	@Override
	public void onConnect() throws NetworkException {
		super.onConnect();
		
		if (client instanceof ConnectChessClient connectClient) {
			connectClient.disconnectMessage = null;
		}
	}
	
	@Override
	public void onPacketDisconnect(PacketDisconnect packet) {
		if (client instanceof ConnectChessClient connectClient) {
			connectClient.disconnectMessage = packet.getMessage();
		}
		
		client.disconnect();
	}
	
	@Override
	public void onPacketChangeContext(CPacketChangeContext packet) {
		client.setContext(packet.getContext());
	}
}
