package muscaa.chess.shared.network.connection.packets;

import java.io.IOException;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import fluff.bin.IBinaryInput;
import fluff.network.packet.IPacketInbound;

public class PacketEncrypt implements IPacketInbound {
	
	private SecretKey key;
	
	public PacketEncrypt() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		key = new SecretKeySpec(in.LenBytes(), "AES");
	}
	
	public SecretKey getKey() {
		return key;
	}
}
