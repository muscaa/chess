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
		
		if (intent == null) {
			connection.disconnect("Invalid intent!");
			return;
		}
		
		System.out.println("Received intent: " + intent.getKey().getID());
		
		if (intent == IntentRegistry.CONNECT.get()) {
			connection.send(new SPacketIntentResponse(true));
			//connection.setContext(ServerContexts.CONNECTION_CONTEXT, new ServerConnectionNetHandler());
			connection.setContext(ServerContextRegistry.CONNECTION.get());
		}
	}
}
