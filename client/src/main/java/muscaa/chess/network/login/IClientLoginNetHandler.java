package muscaa.chess.network.login;

import muscaa.chess.network.login.packets.CPacketLoginSuccess;
import muscaa.chess.shared.network.login.ILoginNetHandler;

public interface IClientLoginNetHandler extends ILoginNetHandler {
	
	void onPacketLoginSuccess(CPacketLoginSuccess packet);
}
