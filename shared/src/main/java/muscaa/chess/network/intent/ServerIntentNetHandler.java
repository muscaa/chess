package muscaa.chess.network.intent;

import muscaa.chess.network.DisconnectReasonRegistry;
import muscaa.chess.network.IntentRegistry;
import muscaa.chess.network.IntentValue;
import muscaa.chess.network.ServerContextRegistry;
import muscaa.chess.network.base.ServerBaseNetHandler;
import muscaa.chess.network.intent.packets.SPacketIntent;

public class ServerIntentNetHandler extends ServerBaseNetHandler implements IServerIntentNetHandler {
	
	@Override
	public void onPacketIntent(SPacketIntent packet) {
		IntentValue intent = packet.getIntent();
		
		if (intent == IntentRegistry.PING.get()) {
			connection.setContext(ServerContextRegistry.PING.get());
		} else if (intent == IntentRegistry.CONNECT.get()) {
			connection.setContext(ServerContextRegistry.CONNECTION.get());
		} else {
			connection.disconnect(DisconnectReasonRegistry.KICK.get(), "Invalid intent!");
		}
	}
}
