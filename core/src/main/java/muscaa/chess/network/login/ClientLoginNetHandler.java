package muscaa.chess.network.login;

import fluff.network.AbstractClientNetHandler;
import muscaa.chess.network.ChessClient;
import muscaa.chess.network.NetworkClient;
import muscaa.chess.network.login.packets.PacketLoginSuccess;
import muscaa.chess.network.play.ClientPlayNetHandler;

public class ClientLoginNetHandler extends AbstractClientNetHandler<ChessClient> implements IClientLoginNetHandler {
	
	@Override
	public void onPacketLoginSuccess(PacketLoginSuccess packet) {
		client.setContext(NetworkClient.PLAY_CONTEXT, new ClientPlayNetHandler());
	}
}
