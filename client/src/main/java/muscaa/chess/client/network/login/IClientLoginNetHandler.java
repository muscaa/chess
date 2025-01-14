package muscaa.chess.client.network.login;

import muscaa.chess.client.network.common.IClientCommonNetHandler;
import muscaa.chess.client.network.login.packets.CPacketLoginForm;
import muscaa.chess.network.login.ILoginNetHandler;

public interface IClientLoginNetHandler extends IClientCommonNetHandler, ILoginNetHandler {
	
	void onPacketLoginForm(CPacketLoginForm packet);
}
