package muscaa.chess.client.player.players;

import muscaa.chess.client.IntegratedServer;
import muscaa.chess.client.player.AbstractClientPlayer;
import muscaa.chess.network.DisconnectReasonRegistry;
import muscaa.chess.network.DisconnectReasonValue;
import muscaa.chess.player.AbstractServerPlayer;

public class LocalClientPlayer extends AbstractClientPlayer {
	
	public IntegratedServer server;
	public AbstractServerPlayer serverPlayer;
	
	@Override
	public void sendChatMessage(String message) {
		serverPlayer.onSendChatMessage(message);
	}
	
	@Override
	public void onDisconnect(DisconnectReasonValue reason, String message) {
		if (reason == DisconnectReasonRegistry.SERVER_STOP.get()) return;
		
		super.onDisconnect(reason, message);
	}
	
	@Override
	public void dispose() {
		super.dispose();
		
		server.stop();
	}
	
	@Override
	public String getName() {
		return serverPlayer.getName();
	}
}
