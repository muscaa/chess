package muscaa.chess.network.connection.packets;

import java.io.IOException;

import javax.crypto.SecretKey;

import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacketOutbound;

public class PacketEncrypt implements IPacketOutbound {
	
	private SecretKey key;
	
	public PacketEncrypt(SecretKey key) {
		this.key = key;
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		out.LenBytes(key.getEncoded());
	}
}
