package muscaa.chess.network.play.packets;

import java.io.IOException;

import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacketOutbound;

public class PacketStartGame implements IPacketOutbound {
	
	public PacketStartGame() {
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
	}
}
