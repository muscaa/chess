package muscaa.chess.network.login;

import muscaa.chess.network.common.IServerCommonNetHandler;
import muscaa.chess.network.login.packets.SPacketLogin;

public interface IServerLoginNetHandler extends IServerCommonNetHandler, ILoginNetHandler {
	
	void onPacketLogin(SPacketLogin packet);
}
