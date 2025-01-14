package muscaa.chess.client.network.login;

import muscaa.chess.client.Client;
import muscaa.chess.client.gui.screens.FormScreen;
import muscaa.chess.client.network.NetworkStatus;
import muscaa.chess.client.network.common.ClientCommonNetHandler;
import muscaa.chess.client.network.login.packets.CPacketLogin;
import muscaa.chess.client.network.login.packets.CPacketLoginForm;
import muscaa.chess.client.utils.TaskManager;

public class ClientLoginNetHandler extends ClientCommonNetHandler implements IClientLoginNetHandler {
	
	@Override
	public void onPacketLoginForm(CPacketLoginForm packet) {
		TaskManager.render(() -> {
			Client.INSTANCE.setScreen(new FormScreen(packet.getForm(), (form, formData) -> {
				client.send(new CPacketLogin(formData));
				client.update(NetworkStatus.LOGIN);
				
				Client.INSTANCE.setScreen(client.getStatusScreen());
			}));
		});
	}
}
