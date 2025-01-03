package muscaa.chess.client.network.login.packets;

import java.io.IOException;

import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacketOutbound;

public class CPacketLogin implements IPacketOutbound {
	
	private String name;
	private String password;
	
	public CPacketLogin(String name, String password) {
		this.name = name;
		this.password = password;
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		out.LenString(name);
		out.LenString(password);
	}
}
