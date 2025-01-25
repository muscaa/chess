package muscaa.chess.client.network.login;

import muscaa.chess.client.network.base.IClientBaseNetHandler;
import muscaa.chess.client.network.login.packets.CPacketProfile;
import muscaa.chess.network.login.ILoginNetHandler;

public interface IClientLoginNetHandler extends IClientBaseNetHandler, ILoginNetHandler {
	
	void onPacketProfile(CPacketProfile packet);
}
