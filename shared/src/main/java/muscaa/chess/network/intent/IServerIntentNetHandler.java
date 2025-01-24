package muscaa.chess.network.intent;

import muscaa.chess.network.base.IServerBaseNetHandler;
import muscaa.chess.network.intent.packets.SPacketIntent;

public interface IServerIntentNetHandler extends IServerBaseNetHandler, IIntentNetHandler {
	
	void onPacketIntent(SPacketIntent packet);
}
