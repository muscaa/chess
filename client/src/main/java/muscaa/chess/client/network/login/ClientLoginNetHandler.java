package muscaa.chess.client.network.login;

import muscaa.chess.client.Client;
import muscaa.chess.client.gui.screens.FormScreen;
import muscaa.chess.client.network.ConnectChessClient;
import muscaa.chess.client.network.NetworkStatus;
import muscaa.chess.client.network.base.ClientBaseNetHandler;
import muscaa.chess.client.network.login.packets.CPacketLogin;
import muscaa.chess.client.network.login.packets.CPacketLoginForm;
import muscaa.chess.client.network.login.packets.CPacketProfile;
import muscaa.chess.client.player.AbstractClientPlayer;
import muscaa.chess.client.player.players.RemoteClientPlayer;
import muscaa.chess.client.utils.TaskManager;

public class ClientLoginNetHandler extends ClientBaseNetHandler implements IClientLoginNetHandler {
	
	@Override
	public void onPacketLoginForm(CPacketLoginForm packet) {
		TaskManager.waitRender(() -> {
			Client.INSTANCE.setScreen(new FormScreen(packet.getForm(), (form, formData) -> {
				client.send(new CPacketLogin(formData));
				
				if (client instanceof ConnectChessClient connectClient) {
					connectClient.update(NetworkStatus.LOGIN);
					
					Client.INSTANCE.setScreen(connectClient.statusScreen);
				}
			}));
		});
	}
	
	@Override
	public void onPacketProfile(CPacketProfile packet) {
		TaskManager.waitRender(() -> {
			AbstractClientPlayer player = new RemoteClientPlayer(client);
			player.setName(packet.getName());
			Client.INSTANCE.setPlayer(player);
		});
	}
}
