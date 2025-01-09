package muscaa.chess.client.network.play.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.network.packet.IPacketInbound;

public class CPacketGameStart implements IPacketInbound {
	
	public CPacketGameStart() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {}
}
