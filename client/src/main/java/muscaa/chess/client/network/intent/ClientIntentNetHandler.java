package muscaa.chess.client.network.intent;

import fluff.network.NetworkException;
import muscaa.chess.Chess;
import muscaa.chess.client.network.ClientContextRegistry;
import muscaa.chess.client.network.NetworkStatus;
import muscaa.chess.client.network.common.ClientCommonNetHandler;
import muscaa.chess.client.network.connection.packets.CPacketEncrypt;
import muscaa.chess.client.network.intent.packets.CPacketIntent;
import muscaa.chess.client.network.intent.packets.CPacketIntentResponse;
import muscaa.chess.network.IntentRegistry;
import muscaa.chess.network.IntentValue;

public class ClientIntentNetHandler extends ClientCommonNetHandler implements IClientIntentNetHandler {
	
	private final IntentValue intent;
	
	public ClientIntentNetHandler(IntentValue intent) {
		this.intent = intent;
	}
	
	@Override
	public void onConnect() throws NetworkException {
		super.onConnect();
		
		client.send(new CPacketIntent(intent));
	}
	
	@Override
	public void onPacketIntentResponse(CPacketIntentResponse packet) {
		if (!packet.isApproved()) {
			client.disconnect();
			return;
		}
		
		if (intent == IntentRegistry.CONNECT.get()) {
			client.setContext(ClientContextRegistry.CONNECTION.get());
			
			client.send(new CPacketEncrypt(Chess.PROTOCOL_VERSION, CPacketEncrypt.generate()));
			client.update(NetworkStatus.ENCRYPT);
		}
	}
}
