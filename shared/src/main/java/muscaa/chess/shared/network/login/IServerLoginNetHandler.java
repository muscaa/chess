package muscaa.chess.shared.network.login;

import muscaa.chess.shared.network.login.packets.PacketLogin;

public interface IServerLoginNetHandler extends ILoginNetHandler {
	
	void onPacketLogin(PacketLogin packet);
}
