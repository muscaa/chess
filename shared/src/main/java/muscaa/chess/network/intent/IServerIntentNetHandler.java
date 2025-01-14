package muscaa.chess.network.intent;

import muscaa.chess.network.common.IServerCommonNetHandler;
import muscaa.chess.network.intent.packets.SPacketIntent;

public interface IServerIntentNetHandler extends IServerCommonNetHandler, IIntentNetHandler {
	
	void onPacketIntent(SPacketIntent packet);
}
