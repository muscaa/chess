package muscaa.chess.network.login.packets;

import java.io.IOException;

import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacketOutbound;

public class CPacketLogin implements IPacketOutbound {
	
	private String name;
	
	public CPacketLogin(String name) {
		this.name = name;
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		out.LenString(name);
	}
}
