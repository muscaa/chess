package muscaa.chess.network.common.packets;

import java.io.IOException;

import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacketOutbound;

public class SPacketChatLine implements IPacketOutbound {
	
	private String line;
	
	public SPacketChatLine(String line) {
		this.line = line;
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		out.LenString(line);
	}
}
