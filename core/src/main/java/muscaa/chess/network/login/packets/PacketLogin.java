package muscaa.chess.network.login.packets;

import java.io.IOException;

import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacketOutbound;

public class PacketLogin implements IPacketOutbound {
	
	private String name;
	
	public PacketLogin(String name) {
		this.name = name;
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		out.LenString(name);
	}
}
