package muscaa.chess.network.intent;

import muscaa.chess.network.Intent;
import muscaa.chess.network.ServerContexts;
import muscaa.chess.network.common.ServerCommonNetHandler;
import muscaa.chess.network.connection.ServerConnectionNetHandler;
import muscaa.chess.network.intent.packets.SPacketIntent;
import muscaa.chess.network.intent.packets.SPacketIntentResponse;
import muscaa.chess.registry.registries.IntentRegistry;

public class ServerIntentNetHandler extends ServerCommonNetHandler implements IServerIntentNetHandler {
	
	@Override
	public void onPacketIntent(SPacketIntent packet) {
		Intent intent = packet.getIntent();
		
		if (intent == null) {
			connection.disconnect("Invalid intent!");
			return;
		}
		
		System.out.println("Received intent: " + intent.getID());
		
		if (intent == IntentRegistry.CONNECT) {
			connection.send(new SPacketIntentResponse(true));
			connection.setContext(ServerContexts.CONNECTION_CONTEXT, new ServerConnectionNetHandler());
		}
	}
}
