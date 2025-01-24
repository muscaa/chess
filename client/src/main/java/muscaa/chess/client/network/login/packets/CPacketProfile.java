package muscaa.chess.client.network.login.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.network.packet.IPacketInbound;

public class CPacketProfile implements IPacketInbound {
	
	private String name;
	
	public CPacketProfile() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		name = in.LenString();
	}
	
	public String getName() {
		return name;
	}
}
