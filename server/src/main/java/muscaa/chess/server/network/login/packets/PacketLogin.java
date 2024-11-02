package muscaa.chess.server.network.login.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.network.packet.IPacketInbound;

public class PacketLogin implements IPacketInbound {
	
	private String name;
	
	public PacketLogin() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		name = in.LenString();
	}
	
	public String getName() {
		return name;
	}
}
