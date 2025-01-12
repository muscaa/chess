package muscaa.chess.network.login.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.network.packet.IPacketInbound;

public class SPacketLogin implements IPacketInbound {
	
	private String name;
	private String password;
	
	public SPacketLogin() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		name = in.LenString();
		password = in.LenString();
	}
	
	public String getName() {
		return name;
	}
	
	public String getPassword() {
		return password;
	}
}
