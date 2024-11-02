package muscaa.chess.server.network.login;

import muscaa.chess.server.network.login.packets.PacketLogin;
import muscaa.chess.shared.network.login.ILoginNetHandler;

public interface IServerLoginNetHandler extends ILoginNetHandler {
	
	void onPacketLogin(PacketLogin packet);
}
