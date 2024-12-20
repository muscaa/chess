package muscaa.chess.client.network.login;

import muscaa.chess.client.network.login.packets.CPacketLoginSuccess;
import muscaa.chess.network.login.ILoginNetHandler;

public interface IClientLoginNetHandler extends ILoginNetHandler {
	
	void onPacketLoginSuccess(CPacketLoginSuccess packet);
}
