package muscaa.chess.client.network.connection.packets;

import java.io.IOException;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import fluff.bin.IBinaryInput;
import fluff.network.packet.IPacketInbound;

public class CPacketHandshake implements IPacketInbound {
	
	private SecretKey key;
	
	public CPacketHandshake() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		key = new SecretKeySpec(in.LenBytes(), "AES");
	}
	
	public SecretKey getKey() {
		return key;
	}
}
