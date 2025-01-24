package muscaa.chess.client.network.common.packets;

import java.io.IOException;

import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacketOutbound;

public class CPacketChatMessage implements IPacketOutbound {
	
	private String message;
	
	public CPacketChatMessage(String message) {
		this.message = message;
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		out.LenString(message);
	}
}
