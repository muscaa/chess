package muscaa.chess.shared.network.login.packets;

import java.io.IOException;

import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacketOutbound;

public class PacketLoginSuccess implements IPacketOutbound {
	
	public PacketLoginSuccess() {}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {}
}
