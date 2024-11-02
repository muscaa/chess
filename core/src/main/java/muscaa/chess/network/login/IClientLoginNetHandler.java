package muscaa.chess.network.login;

import muscaa.chess.network.login.packets.PacketLoginSuccess;
import muscaa.chess.shared.network.login.ILoginNetHandler;

public interface IClientLoginNetHandler extends ILoginNetHandler {
	
	void onPacketLoginSuccess(PacketLoginSuccess packet);
}
