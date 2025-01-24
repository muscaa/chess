package muscaa.chess.network.login;

import muscaa.chess.network.base.IServerBaseNetHandler;
import muscaa.chess.network.login.packets.SPacketLogin;

public interface IServerLoginNetHandler extends IServerBaseNetHandler, ILoginNetHandler {
	
	void onPacketLogin(SPacketLogin packet);
}
