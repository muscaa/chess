package muscaa.chess.network.connection;

import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import fluff.network.AbstractClientNetHandler;
import fluff.network.NetworkException;
import fluff.network.packet.channels.EncryptedPacketChannel;
import muscaa.chess.network.ChessClient;
import muscaa.chess.network.NetworkClient;
import muscaa.chess.network.connection.packets.PacketEncrypt;
import muscaa.chess.network.connection.packets.PacketHandshake;
import muscaa.chess.network.login.ClientLoginNetHandler;
import muscaa.chess.network.login.packets.PacketLogin;

public class ClientConnectionNetHandler extends AbstractClientNetHandler<ChessClient> implements IClientConnectionNetHandler {
	
	@Override
	public void onConnect() throws NetworkException {
		client.send(new PacketEncrypt(generate()));
	}
	
	@Override
	public void onPacketHandshake(PacketHandshake packet) {
		client.setChannel(new EncryptedPacketChannel(packet.getKey()));
		client.setContext(NetworkClient.LOGIN_CONTEXT, new ClientLoginNetHandler());
		
		client.send(new PacketLogin("muscaa"));
	}
	
	private static SecretKey generate() {
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128);
			return keyGenerator.generateKey();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}
