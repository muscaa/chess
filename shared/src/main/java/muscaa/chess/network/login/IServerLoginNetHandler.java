package muscaa.chess.network.login;

import muscaa.chess.network.login.packets.PacketLogin;

public interface IServerLoginNetHandler extends ILoginNetHandler {
	
	void onPacketLogin(PacketLogin packet);
}
