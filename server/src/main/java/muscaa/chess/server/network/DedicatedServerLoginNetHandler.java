package muscaa.chess.server.network;

import muscaa.chess.AbstractServer;
import muscaa.chess.form.Form;
import muscaa.chess.form.FormData;
import muscaa.chess.form.button.FormButtonWidget;
import muscaa.chess.form.button.FormButtonWidgetData;
import muscaa.chess.form.field.FormFieldRegistry;
import muscaa.chess.form.field.FormFieldWidget;
import muscaa.chess.form.field.FormFieldWidgetData;
import muscaa.chess.network.DisconnectReasonRegistry;
import muscaa.chess.network.base.packets.SPacketForm;
import muscaa.chess.network.base.packets.SPacketFormData;
import muscaa.chess.network.login.ServerLoginNetHandler;
import muscaa.chess.player.players.RemoteServerPlayer;
import muscaa.chess.server.Server;
import muscaa.chess.server.database.impl.IAccountsTable;

public class DedicatedServerLoginNetHandler extends ServerLoginNetHandler {
	
	protected final Form registerForm;
	
	public DedicatedServerLoginNetHandler(AbstractServer gameServer) {
		super(gameServer);
		
		registerForm = buildRegisterForm();
	}
	
	protected Form buildRegisterForm() {
		Form form = new Form("register", "Register");
		form.add(new FormFieldWidget("name", FormFieldRegistry.STRING.get(), "Name"));
		form.add(new FormFieldWidget("password", FormFieldRegistry.STRING.get(), "Password"));
		form.add(new FormFieldWidget("repeat_password", FormFieldRegistry.STRING.get(), "Repeat Password"));
		form.add(new FormFieldWidget("display_name", FormFieldRegistry.STRING.get(), "Display Name"));
		form.add(new FormButtonWidget("register", "Register"));
		form.add(new FormButtonWidget("login", "Existing Account"));
		return form;
	}
	
	protected IAccountsTable.Account register(FormData registerData) throws Exception {
		FormFieldWidget nameField = registerForm.get("name");
		FormFieldWidgetData nameFieldData = registerData.get("name");
		String name = nameField.get(nameFieldData);
		
		FormFieldWidget passwordField = registerForm.get("password");
		FormFieldWidgetData passwordFieldData = registerData.get("password");
		String password = passwordField.get(passwordFieldData);
		
		FormFieldWidget repeatPasswordField = registerForm.get("repeat_password");
		FormFieldWidgetData repeatPasswordFieldData = registerData.get("repeat_password");
		String repeatPassword = repeatPasswordField.get(repeatPasswordFieldData);
		
		FormFieldWidget displayNameField = registerForm.get("display_name");
		FormFieldWidgetData displayNameFieldData = registerData.get("display_name");
		String displayName = displayNameField.get(displayNameFieldData);
		
		FormButtonWidget registerButton = registerForm.get("register");
		FormButtonWidgetData registerButtonData = registerData.get("register");
		boolean registerPressed = registerButton.isPressed(registerButtonData);
		
		FormButtonWidget loginButton = registerForm.get("login");
		FormButtonWidgetData loginButtonData = registerData.get("login");
		boolean loginPressed = loginButton.isPressed(loginButtonData);
		
		if (loginPressed && !registerPressed) {
			connection.send(new SPacketForm(loginForm));
			return null;
		}
		
		return Server.INSTANCE.getMainTables().getAccounts().register(name, password, repeatPassword, displayName);
	}
	
	@Override
	protected Form buildLoginForm() {
		Form form = new Form("login", "Login");
		form.add(new FormFieldWidget("name", FormFieldRegistry.STRING.get(), "Name"));
		form.add(new FormFieldWidget("password", FormFieldRegistry.STRING.get(), "Password"));
		form.add(new FormButtonWidget("login", "Login"));
		form.add(new FormButtonWidget("register", "New Account"));
		return form;
	}
	
	protected IAccountsTable.Account login(FormData loginData) throws Exception {
		FormFieldWidget nameField = loginForm.get("name");
		FormFieldWidgetData nameFieldData = loginData.get("name");
		String name = nameField.get(nameFieldData);
		
		FormFieldWidget passwordField = loginForm.get("password");
		FormFieldWidgetData passwordFieldData = loginData.get("password");
		String password = passwordField.get(passwordFieldData);
		
		FormButtonWidget loginButton = loginForm.get("login");
		FormButtonWidgetData loginButtonData = loginData.get("login");
		boolean loginPressed = loginButton.isPressed(loginButtonData);
		
		FormButtonWidget registerButton = loginForm.get("register");
		FormButtonWidgetData registerButtonData = loginData.get("register");
		boolean registerPressed = registerButton.isPressed(registerButtonData);
		
		if (registerPressed && !loginPressed) {
			connection.send(new SPacketForm(registerForm));
			return null;
		}
		
		return Server.INSTANCE.getMainTables().getAccounts().login(name, password);
	}
	
	@Override
	public void onPacketFormData(SPacketFormData packet) {
		FormData data = packet.getFormData();
		
		if (data.id.equals(registerForm.id)) {
			if (!registerForm.isValid(data)) {
				connection.disconnect(DisconnectReasonRegistry.KICK.get(), "Invalid register form data!");
				return;
			}
			
			try {
				IAccountsTable.Account account = register(data);
				if (account == null) return;
				
				connection.login(account.uuid());
				
				connection.player = new RemoteServerPlayer(connection);
				connection.player.setName(account.displayName());
			} catch (Exception e) {
				connection.disconnect(DisconnectReasonRegistry.KICK.get(), e.getMessage());
			}
		} else if (data.id.equals(loginForm.id)) {
			if (!loginForm.isValid(data)) {
				connection.disconnect(DisconnectReasonRegistry.KICK.get(), "Invalid login form data!");
				return;
			}
			
			try {
				IAccountsTable.Account account = login(data);
				if (account == null) return;
				
				connection.login(account.uuid());
				
				connection.player = new RemoteServerPlayer(connection);
				connection.player.setName(account.displayName());
			} catch (Exception e) {
				connection.disconnect(DisconnectReasonRegistry.KICK.get(), e.getMessage());
			}
		} else {
			super.onPacketFormData(packet);
		}
	}
}
