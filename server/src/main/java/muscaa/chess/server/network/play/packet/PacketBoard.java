package muscaa.chess.server.network.play.packet;

import java.io.IOException;

import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacketOutbound;

public class PacketBoard implements IPacketOutbound {
	
	public PacketBoard() {
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
	}
}
