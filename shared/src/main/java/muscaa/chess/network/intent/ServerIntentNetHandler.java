package muscaa.chess.network.intent;

import muscaa.chess.network.IntentRegistry;
import muscaa.chess.network.IntentValue;
import muscaa.chess.network.ServerContextRegistry;
import muscaa.chess.network.common.ServerCommonNetHandler;
import muscaa.chess.network.intent.packets.SPacketIntent;
import muscaa.chess.network.intent.packets.SPacketIntentResponse;

public class ServerIntentNetHandler extends ServerCommonNetHandler implements IServerIntentNetHandler {
	
	@Override
	public void onPacketIntent(SPacketIntent packet) {
		IntentValue intent = packet.getIntent();
		
		if (intent == IntentRegistry.CONNECT.get()) {
			//connection.send(new SPacketIntentResponse(true));
			connection.setContext(ServerContextRegistry.CONNECTION.get());
		} else {
			connection.disconnect("Invalid intent!");
		}
	}
}
