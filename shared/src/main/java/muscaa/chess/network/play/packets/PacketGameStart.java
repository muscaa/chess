package muscaa.chess.network.play.packets;

import java.io.IOException;

import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacketOutbound;

public class PacketGameStart implements IPacketOutbound {
	
	public PacketGameStart() {
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
	}
}
