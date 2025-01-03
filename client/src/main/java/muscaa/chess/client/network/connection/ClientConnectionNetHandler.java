package muscaa.chess.client.network.connection;

import fluff.network.packet.channels.EncryptedPacketChannel;
import muscaa.chess.client.Client;
import muscaa.chess.client.gui.screens.LoginScreen;
import muscaa.chess.client.network.ClientContexts;
import muscaa.chess.client.network.common.ClientCommonNetHandler;
import muscaa.chess.client.network.connection.packets.CPacketHandshake;
import muscaa.chess.client.network.login.ClientLoginNetHandler;
import muscaa.chess.client.utils.TaskManager;

public class ClientConnectionNetHandler extends ClientCommonNetHandler implements IClientConnectionNetHandler {
	
	@Override
	public void onPacketHandshake(CPacketHandshake packet) {
		client.setChannel(new EncryptedPacketChannel(packet.getKey()));
		client.setContext(ClientContexts.LOGIN_CONTEXT, new ClientLoginNetHandler());
		
		TaskManager.render(() -> {
			Client.INSTANCE.getGuiLayer().setScreen(new LoginScreen(client, Client.INSTANCE.getGuiLayer().getScreen()));
		});
	}
}
