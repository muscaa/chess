package muscaa.chess.network.login;

import fluff.network.NetworkException;
import fluff.network.client.IClient;
import muscaa.chess.form.Form;
import muscaa.chess.form.FormData;
import muscaa.chess.form.field.FormField;
import muscaa.chess.form.field.FormFieldData;
import muscaa.chess.form.field.FormFieldRegistry;
import muscaa.chess.network.ServerContextRegistry;
import muscaa.chess.network.common.ServerCommonNetHandler;
import muscaa.chess.network.login.packets.SPacketLogin;
import muscaa.chess.network.login.packets.SPacketLoginForm;

public class ServerLoginNetHandler extends ServerCommonNetHandler implements IServerLoginNetHandler {
	
	private Form loginForm;
	
	public ServerLoginNetHandler() {
		loginForm = new Form("login", "Login", "Login");
		loginForm.add(new FormField("name", FormFieldRegistry.STRING.get(), "Name"));
	}
	
	protected void handleLogin(FormData loginData) {
		FormField nameField = loginForm.get("name");
		FormFieldData nameFieldData = loginData.get("name");
		
		String name = nameField.get(nameFieldData);
		
		connection.login(name);
	}
	
	@Override
	public void onInit(IClient client) {
		super.onInit(client);
		
		connection.send(new SPacketLoginForm(loginForm));
	}
	
	@Override
	public void onPacketLogin(SPacketLogin packet) {
		FormData loginData = packet.getFormData();
		if (!loginForm.isValid(loginData)) {
			connection.disconnect("Invalid login form data!");
			return;
		}
		
		handleLogin(loginData);
	}
	
	@Override
	public void onConnect() throws NetworkException {
		connection.setContext(ServerContextRegistry.PLAY.get());
	}
}
