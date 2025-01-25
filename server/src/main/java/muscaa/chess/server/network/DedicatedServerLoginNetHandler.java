package muscaa.chess.server.network;

import muscaa.chess.AbstractServer;
import muscaa.chess.form.Form;
import muscaa.chess.form.FormData;
import muscaa.chess.form.field.FormField;
import muscaa.chess.form.field.FormFieldData;
import muscaa.chess.form.field.FormFieldRegistry;
import muscaa.chess.network.login.ServerLoginNetHandler;
import muscaa.chess.player.players.RemoteServerPlayer;
import muscaa.chess.server.Server;

public class DedicatedServerLoginNetHandler extends ServerLoginNetHandler {
	
	public DedicatedServerLoginNetHandler(AbstractServer gameServer) {
		super(gameServer);
	}
	
	@Override
	protected Form buildLoginForm() {
		Form form = DEFAULT_LOGIN_FORM.copy();
		form.add(new FormField("password", FormFieldRegistry.STRING.get(), "Password"));
		return form;
	}
	
	@Override
	protected void handleLoginData(FormData loginData) {
		FormField nameField = loginForm.get("name");
		FormField passwordField = loginForm.get("password");
		
		FormFieldData nameFieldData = loginData.get("name");
		FormFieldData passwordFieldData = loginData.get("password");
		
		String name = nameField.get(nameFieldData);
		String password = passwordField.get(passwordFieldData);
		
		try {
			Server.INSTANCE.getMainTables().getAccounts().addAccount(name, password, name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		connection.login(name);
		
		connection.player = new RemoteServerPlayer(connection);
		connection.player.setName(name);
	}
}
