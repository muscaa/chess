package muscaa.chess.client.network.base;

import fluff.network.AbstractClientNetHandler;
import fluff.network.NetworkException;
import muscaa.chess.client.Client;
import muscaa.chess.client.gui.screens.DisconnectedScreen;
import muscaa.chess.client.network.AbstractChessClient;
import muscaa.chess.client.network.ConnectChessClient;
import muscaa.chess.client.network.base.packets.CPacketChangeContext;
import muscaa.chess.client.utils.TaskManager;
import muscaa.chess.network.base.packets.PacketDisconnect;

public abstract class ClientBaseNetHandler extends AbstractClientNetHandler<AbstractChessClient> implements IClientBaseNetHandler {
	
	@Override
	public void onConnect() throws NetworkException {
		super.onConnect();
		
		if (client instanceof ConnectChessClient connectClient) {
			connectClient.disconnectReason = null;
			connectClient.disconnectMessage = null;
		}
	}
	
	@Override
	public void onPacketDisconnect(PacketDisconnect packet) {
		if (client instanceof ConnectChessClient connectClient) {
			connectClient.disconnectReason = packet.getReason();
			connectClient.disconnectMessage = packet.getMessage();
		}
		
		System.out.println("Disconnect: " + packet.getReason() + " " + packet.getMessage());
		
		if (Client.INSTANCE.getPlayer() != null) {
			Client.INSTANCE.getPlayer().onDisconnect(packet.getReason(), packet.getMessage());
		} else {
			TaskManager.render(() -> {
				Client.INSTANCE.setScreen(new DisconnectedScreen(packet.getMessage()));
			});
		}
		
		client.disconnect();
	}
	
	@Override
	public void onPacketChangeContext(CPacketChangeContext packet) {
		client.setContext(packet.getContext());
	}
}
