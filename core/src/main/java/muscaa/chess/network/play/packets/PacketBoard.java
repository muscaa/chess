package muscaa.chess.network.play.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.network.packet.IPacketInbound;

public class PacketBoard implements IPacketInbound {
	
	public PacketBoard() {
	}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
	}
}
