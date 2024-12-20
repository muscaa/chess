package muscaa.chess.client.network.common;

import fluff.network.AbstractClientNetHandler;
import fluff.network.NetworkException;
import muscaa.chess.client.Core;
import muscaa.chess.client.gui.screens.DisconnectedScreen;
import muscaa.chess.client.network.ChessClient;
import muscaa.chess.client.task.TaskManager;
import muscaa.chess.network.common.ICommonNetHandler;
import muscaa.chess.network.common.packets.PacketDisconnect;

public class ClientCommonNetHandler extends AbstractClientNetHandler<ChessClient> implements ICommonNetHandler {
	
	private boolean connected;
	
	@Override
	public void onConnect() throws NetworkException {
		super.onConnect();
		
		connected = true;
	}
	
	@Override
	public void onDisconnect() {
		if (!connected) return;
		connected = false;
		
		onPacketDisconnect(new PacketDisconnect("Disconnected!"));
	}
	
	@Override
	public void onPacketDisconnect(PacketDisconnect packet) {
		/*if (!connected) return;
		connected = false;*/
		// TODO fix this
		
		TaskManager.render(() -> {
			Core.INSTANCE.getGuiLayer().setScreen(new DisconnectedScreen(packet.getMessage()));
		});
		
		client.disconnect();
	}
}
