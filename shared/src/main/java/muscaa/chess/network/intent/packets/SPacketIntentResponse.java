package muscaa.chess.network.intent.packets;

import java.io.IOException;

import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacketOutbound;

public class SPacketIntentResponse implements IPacketOutbound {
	
	private boolean approved;
	
	public SPacketIntentResponse(boolean approved) {
		this.approved = approved;
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		out.Boolean(approved);
	}
}
