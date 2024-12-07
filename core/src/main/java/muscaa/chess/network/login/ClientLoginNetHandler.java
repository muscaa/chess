package muscaa.chess.network.login;

import muscaa.chess.ChessGame;
import muscaa.chess.network.ClientContexts;
import muscaa.chess.network.NetworkStatus;
import muscaa.chess.network.common.ClientCommonNetHandler;
import muscaa.chess.network.login.packets.PacketLoginSuccess;
import muscaa.chess.network.play.ClientPlayNetHandler;

public class ClientLoginNetHandler extends ClientCommonNetHandler implements IClientLoginNetHandler {
	
	@Override
	public void onPacketLoginSuccess(PacketLoginSuccess packet) {
		client.setContext(ClientContexts.PLAY_CONTEXT, new ClientPlayNetHandler());
		
		ChessGame.INSTANCE.getNetwork().update(NetworkStatus.DONE);
	}
}
