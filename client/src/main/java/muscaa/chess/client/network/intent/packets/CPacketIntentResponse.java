package muscaa.chess.client.network.intent.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.network.packet.IPacketInbound;

public class CPacketIntentResponse implements IPacketInbound {
	
	private boolean approved;
	
	public CPacketIntentResponse() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		approved = in.Boolean();
	}
	
	public boolean isApproved() {
		return approved;
	}
}
