package muscaa.chess.network.login.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.network.packet.IPacketInbound;

public class PacketLoginSuccess implements IPacketInbound {
	
	public PacketLoginSuccess() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
	}
}
