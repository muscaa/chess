package muscaa.chess.network.common.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.network.packet.IPacketInbound;

public class SPacketChatMessage implements IPacketInbound {
	
	private String message;
	
	public SPacketChatMessage() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		message = in.LenString();
	}
	
	public String getMessage() {
		return message;
	}
}
