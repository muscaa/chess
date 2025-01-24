package muscaa.chess.client.player.players;

import muscaa.chess.client.network.AbstractChessClient;
import muscaa.chess.client.network.common.packets.CPacketChatMessage;
import muscaa.chess.client.player.AbstractClientPlayer;

public class RemoteClientPlayer extends AbstractClientPlayer {
	
	protected final AbstractChessClient client;
	
	public RemoteClientPlayer(AbstractChessClient client) {
		this.client = client;
	}
	
	@Override
	public void sendChatMessage(String message) {
		client.send(new CPacketChatMessage(message));
	}
	
	@Override
	public void dispose() {
		super.dispose();
		
		client.disconnect();
	}
}
