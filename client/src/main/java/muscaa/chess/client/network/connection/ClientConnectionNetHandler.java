package muscaa.chess.client.network.connection;

import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import fluff.network.NetworkException;
import fluff.network.packet.channels.EncryptedPacketChannel;
import muscaa.chess.client.Core;
import muscaa.chess.client.network.ClientContexts;
import muscaa.chess.client.network.NetworkStatus;
import muscaa.chess.client.network.common.ClientCommonNetHandler;
import muscaa.chess.client.network.connection.packets.CPacketEncrypt;
import muscaa.chess.client.network.connection.packets.CPacketHandshake;
import muscaa.chess.client.network.login.ClientLoginNetHandler;
import muscaa.chess.client.network.login.packets.CPacketLogin;

public class ClientConnectionNetHandler extends ClientCommonNetHandler implements IClientConnectionNetHandler {
	
	@Override
	public void onConnect() throws NetworkException {
		super.onConnect();
		
		client.send(new CPacketEncrypt(generate()));
		
		Core.INSTANCE.getNetwork().update(NetworkStatus.ENCRYPT);
	}
	
	@Override
	public void onPacketHandshake(CPacketHandshake packet) {
		client.setChannel(new EncryptedPacketChannel(packet.getKey()));
		client.setContext(ClientContexts.LOGIN_CONTEXT, new ClientLoginNetHandler());
		
		client.send(new CPacketLogin(Core.INSTANCE.getNetwork().getName()));
		
		Core.INSTANCE.getNetwork().update(NetworkStatus.LOGIN);
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
