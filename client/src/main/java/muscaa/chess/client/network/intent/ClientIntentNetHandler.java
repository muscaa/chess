package muscaa.chess.client.network.intent;

import fluff.network.NetworkException;
import muscaa.chess.Chess;
import muscaa.chess.client.network.ClientContexts;
import muscaa.chess.client.network.NetworkStatus;
import muscaa.chess.client.network.common.ClientCommonNetHandler;
import muscaa.chess.client.network.connection.ClientConnectionNetHandler;
import muscaa.chess.client.network.connection.packets.CPacketEncrypt;
import muscaa.chess.client.network.intent.packets.CPacketIntent;
import muscaa.chess.client.network.intent.packets.CPacketIntentResponse;
import muscaa.chess.network.Intent;
import muscaa.chess.registry.registries.IntentRegistry;

public class ClientIntentNetHandler extends ClientCommonNetHandler implements IClientIntentNetHandler {
	
	private final Intent intent;
	
	public ClientIntentNetHandler(Intent intent) {
		this.intent = intent;
	}
	
	@Override
	public void onConnect() throws NetworkException {
		client.send(new CPacketIntent(intent));
	}
	
	@Override
	public void onPacketIntentResponse(CPacketIntentResponse packet) {
		if (!packet.isApproved()) {
			client.disconnect();
			return;
		}
		
		if (intent == IntentRegistry.CONNECT) {
			client.setContext(ClientContexts.CONNECTION_CONTEXT, new ClientConnectionNetHandler());
			
			client.send(new CPacketEncrypt(Chess.PROTOCOL_VERSION, CPacketEncrypt.generate()));
			client.update(NetworkStatus.ENCRYPT);
		}
	}
}
