package muscaa.chess.client.network.common.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.network.packet.IPacketInbound;

public class CPacketChatLine implements IPacketInbound {
	
	private String line;
	
	public CPacketChatLine() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		line = in.LenString();
	}
	
	public String getLine() {
		return line;
	}
}
