package muscaa.chess.network.common;

import fluff.network.AbstractClientNetHandler;
import fluff.network.NetworkException;
import muscaa.chess.ChessGame;
import muscaa.chess.gui.screens.DisconnectedScreen;
import muscaa.chess.network.ChessClient;
import muscaa.chess.shared.network.common.ICommonNetHandler;
import muscaa.chess.shared.network.common.packets.PacketDisconnect;
import muscaa.chess.task.TaskManager;

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
		if (!connected) return;
		connected = false;
		
		client.disconnect();
		
		String message = packet.getMessage();
		TaskManager.render(() -> {
			ChessGame.INSTANCE.getGuiLayer().setScreen(new DisconnectedScreen(message));
		});
	}
}
