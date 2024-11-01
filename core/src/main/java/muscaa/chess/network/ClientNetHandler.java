package muscaa.chess.network;

import fluff.network.AbstractClientNetHandler;
import muscaa.chess.shared.PacketMessage;

public class ClientNetHandler extends AbstractClientNetHandler implements IClientNetHandler {
	
	@Override
	public void onPacketMessage(PacketMessage packet) {
		System.out.println("server: " + packet.getMessage());
	}
}
