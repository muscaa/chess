package muscaa.chess.network.intent;

import muscaa.chess.network.intent.packets.SPacketIntent;

public interface IServerIntentNetHandler extends IIntentNetHandler {
	
	void onPacketIntent(SPacketIntent packet);
}
