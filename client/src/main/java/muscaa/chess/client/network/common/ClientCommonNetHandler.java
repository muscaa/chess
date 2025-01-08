package muscaa.chess.client.network.common;

import fluff.network.AbstractClientNetHandler;
import fluff.network.NetworkException;
import muscaa.chess.client.Client;
import muscaa.chess.client.gui.screens.DisconnectedScreen;
import muscaa.chess.client.network.ChessClient;
import muscaa.chess.client.utils.TaskManager;
import muscaa.chess.network.common.packets.PacketDisconnect;

public class ClientCommonNetHandler extends AbstractClientNetHandler<ChessClient> implements IClientCommonNetHandler {
	
	private String disconnectMessage;
	
	@Override
	public void onConnect() throws NetworkException {
		super.onConnect();
		
		disconnectMessage = null;
	}
	
	@Override
	public void onDisconnect() {
		TaskManager.render(() -> {
			Client.INSTANCE.getGuiLayer().setScreen(new DisconnectedScreen(disconnectMessage));
		});
	}
	
	@Override
	public void onPacketDisconnect(PacketDisconnect packet) {
		disconnectMessage = packet.getMessage();
		
		client.disconnect();
	}
}
