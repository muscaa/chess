package muscaa.chess.client.network.login;

import muscaa.chess.client.network.ClientContexts;
import muscaa.chess.client.network.NetworkStatus;
import muscaa.chess.client.network.common.ClientCommonNetHandler;
import muscaa.chess.client.network.login.packets.CPacketLoginSuccess;
import muscaa.chess.client.network.play.ClientPlayNetHandler;

public class ClientLoginNetHandler extends ClientCommonNetHandler implements IClientLoginNetHandler {
	
	@Override
	public void onPacketLoginSuccess(CPacketLoginSuccess packet) {
		client.setContext(ClientContexts.PLAY_CONTEXT, new ClientPlayNetHandler());
		
		client.update(NetworkStatus.DONE);
	}
}
