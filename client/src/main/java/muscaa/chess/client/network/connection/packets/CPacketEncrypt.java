package muscaa.chess.client.network.connection.packets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacketOutbound;

public class CPacketEncrypt implements IPacketOutbound {
	
	private int protocolVersion;
	private SecretKey key;
	
	public CPacketEncrypt(int protocolVersion, SecretKey key) {
		this.protocolVersion = protocolVersion;
        this.key = key;
    }
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		out.Int(protocolVersion);
		out.LenBytes(key.getEncoded());
	}
	
	public static SecretKey generate() {
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128);
			return keyGenerator.generateKey();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}
