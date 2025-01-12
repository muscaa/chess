package muscaa.chess.network.play.packets;

import java.io.IOException;

import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacketOutbound;

public class SPacketGameStart implements IPacketOutbound {
	
	public SPacketGameStart() {
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
	}
}
