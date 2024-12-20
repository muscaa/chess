package muscaa.chess.network.login;

import muscaa.chess.Core;
import muscaa.chess.network.ClientContexts;
import muscaa.chess.network.NetworkStatus;
import muscaa.chess.network.common.ClientCommonNetHandler;
import muscaa.chess.network.login.packets.CPacketLoginSuccess;
import muscaa.chess.network.play.ClientPlayNetHandler;

public class ClientLoginNetHandler extends ClientCommonNetHandler implements IClientLoginNetHandler {
	
	@Override
	public void onPacketLoginSuccess(CPacketLoginSuccess packet) {
		client.setContext(ClientContexts.PLAY_CONTEXT, new ClientPlayNetHandler());
		
		Core.INSTANCE.getNetwork().update(NetworkStatus.DONE);
	}
}
