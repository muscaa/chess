package muscaa.chess.network.connection.packets;

import java.io.IOException;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import fluff.bin.IBinaryInput;
import fluff.network.packet.IPacketInbound;

public class PacketEncrypt implements IPacketInbound {
	
	private int protocolVersion;
	private SecretKey key;
	
	public PacketEncrypt() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		protocolVersion = in.Int();
		key = new SecretKeySpec(in.LenBytes(), "AES");
	}
	
	public int getProtocolVersion() {
		return protocolVersion;
	}
	
	public SecretKey getKey() {
		return key;
	}
}
