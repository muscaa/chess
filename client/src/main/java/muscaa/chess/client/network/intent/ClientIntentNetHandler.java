package muscaa.chess.client.network.intent;

import fluff.network.NetworkException;
import muscaa.chess.client.network.base.ClientBaseNetHandler;
import muscaa.chess.client.network.intent.packets.CPacketIntent;
import muscaa.chess.network.IntentValue;

public class ClientIntentNetHandler extends ClientBaseNetHandler implements IClientIntentNetHandler {
	
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
