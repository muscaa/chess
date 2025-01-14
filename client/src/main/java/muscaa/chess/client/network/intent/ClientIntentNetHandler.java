package muscaa.chess.client.network.intent;

import fluff.network.NetworkException;
import muscaa.chess.client.network.common.ClientCommonNetHandler;
import muscaa.chess.client.network.intent.packets.CPacketIntent;
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
}
