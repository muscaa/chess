package muscaa.chess.server;

import fluff.network.AbstractServerNetHandler;
import muscaa.chess.shared.PacketMessage;

public class ServerNetHandler extends AbstractServerNetHandler implements IServerNetHandler {
	
	@Override
	public void onPacketMessage(PacketMessage packet) {
		System.out.println("client: " + packet.getMessage());
	}
}
