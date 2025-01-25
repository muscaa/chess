package muscaa.chess.server.network;

import muscaa.chess.AbstractServer;
import muscaa.chess.form.Form;
import muscaa.chess.form.FormData;
import muscaa.chess.form.button.FormButtonWidget;
import muscaa.chess.form.button.FormButtonWidgetData;
import muscaa.chess.form.field.FormFieldRegistry;
import muscaa.chess.form.field.FormFieldWidget;
import muscaa.chess.form.field.FormFieldWidgetData;
import muscaa.chess.network.login.ServerLoginNetHandler;
import muscaa.chess.server.Server;

public class DedicatedServerLoginNetHandler extends ServerLoginNetHandler {
	
	protected Form registerForm;
	
	public DedicatedServerLoginNetHandler(AbstractServer gameServer) {
		super(gameServer);
	}
	
	@Override
	protected Form buildLoginForm() {
		Form form = new Form("login", "Login");
		form.add(new FormFieldWidget("name", FormFieldRegistry.STRING.get(), "Name"));
		form.add(new FormFieldWidget("password", FormFieldRegistry.STRING.get(), "Password"));
		form.add(new FormButtonWidget("submit", "Login"));
		return form;
	}
	
	@Override
	protected String handleLoginData(FormData loginData) {
		FormFieldWidget nameField = loginForm.get("name");
		FormFieldWidgetData nameFieldData = loginData.get("name");
		String name = nameField.get(nameFieldData);
		
		FormFieldWidget passwordField = loginForm.get("password");
		FormFieldWidgetData passwordFieldData = loginData.get("password");
		String password = passwordField.get(passwordFieldData);
		
		FormButtonWidget submitButton = loginForm.get("submit");
		FormButtonWidgetData submitButtonData = loginData.get("submit");
		boolean pressed = submitButton.isPressed(submitButtonData);
		
		if (!pressed) return null;
		
		try {
			Server.INSTANCE.getMainTables().getAccounts().addAccount(name, password, name);
			
			return name;
		} catch (Exception e) {}
		
		return null;
	}
}
