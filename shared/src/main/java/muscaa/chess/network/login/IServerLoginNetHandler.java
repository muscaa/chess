package muscaa.chess.network.login;

import muscaa.chess.network.login.packets.SPacketLogin;

public interface IServerLoginNetHandler extends ILoginNetHandler {
	
	void onPacketLogin(SPacketLogin packet);
}
