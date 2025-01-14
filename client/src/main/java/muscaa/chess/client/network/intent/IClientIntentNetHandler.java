package muscaa.chess.client.network.intent;

import muscaa.chess.client.network.common.IClientCommonNetHandler;
import muscaa.chess.client.network.intent.packets.CPacketIntentResponse;
import muscaa.chess.network.intent.IIntentNetHandler;

public interface IClientIntentNetHandler extends IClientCommonNetHandler, IIntentNetHandler {
	
	//void onPacketIntentResponse(CPacketIntentResponse packet);
}
