package muscaa.chess.network.login;

import fluff.network.NetworkException;
import fluff.network.client.IClient;
import muscaa.chess.AbstractServer;
import muscaa.chess.form.Form;
import muscaa.chess.form.FormData;
import muscaa.chess.form.button.FormButtonWidget;
import muscaa.chess.form.button.FormButtonWidgetData;
import muscaa.chess.form.field.FormFieldRegistry;
import muscaa.chess.form.field.FormFieldWidget;
import muscaa.chess.form.field.FormFieldWidgetData;
import muscaa.chess.network.DisconnectReasonRegistry;
import muscaa.chess.network.ServerContextRegistry;
import muscaa.chess.network.base.ServerBaseNetHandler;
import muscaa.chess.network.base.packets.SPacketForm;
import muscaa.chess.network.base.packets.SPacketFormData;
import muscaa.chess.network.login.packets.SPacketProfile;
import muscaa.chess.player.players.RemoteServerPlayer;

public class ServerLoginNetHandler extends ServerBaseNetHandler implements IServerLoginNetHandler {
	
	public static final Form DEFAULT_LOGIN_FORM;
	static {
		DEFAULT_LOGIN_FORM = new Form("login", "Login");
		DEFAULT_LOGIN_FORM.add(new FormFieldWidget("name", FormFieldRegistry.STRING.get(), "Name"));
		// TODO add empty space
		DEFAULT_LOGIN_FORM.add(new FormButtonWidget("submit", "Login"));
	}
	
	protected final AbstractServer gameServer;
	protected final Form loginForm;
	
	public ServerLoginNetHandler(AbstractServer gameServer) {
		this.gameServer = gameServer;
		
		loginForm = buildLoginForm();
	}
	
	public static String handleDefaultLoginData(Form loginForm, FormData loginData) {
		FormFieldWidget nameField = loginForm.get("name");
		FormFieldWidgetData nameFieldData = loginData.get("name");
		String name = nameField.get(nameFieldData);
		
		FormButtonWidget submitButton = loginForm.get("submit");
		FormButtonWidgetData submitButtonData = loginData.get("submit");
		boolean pressed = submitButton.isPressed(submitButtonData);
		
		if (!pressed) return null;
		
		return name;
	}
	
	protected Form buildLoginForm() {
		return DEFAULT_LOGIN_FORM;
	}
	
	protected String handleLoginData(FormData loginData) {
		return handleDefaultLoginData(loginForm, loginData);
	}
	
	@Override
	public void onInit(IClient client) {
		super.onInit(client);
		
		connection.send(new SPacketForm(loginForm));
	}
	
	@Override
	public void onPacketFormData(SPacketFormData packet) {
		FormData loginData = packet.getFormData();
		if (!loginData.id.equals(loginForm.id)) {
			super.onPacketFormData(packet);
			return;
		}
		
		if (!loginForm.isValid(loginData)) {
			connection.disconnect(DisconnectReasonRegistry.KICK.get(), "Invalid login form data!");
			return;
		}
		
		String name = handleLoginData(loginData);
		if (name == null) {
			connection.disconnect(DisconnectReasonRegistry.KICK.get(), "Invalid login form data!");
			return;
		}
		
		connection.login(name);
		
		connection.player = new RemoteServerPlayer(connection);
		connection.player.setName(name);
	}
	
	@Override
	public void onConnect() throws NetworkException {
		connection.send(new SPacketProfile(connection.player.getName()));
		gameServer.addPlayer(connection.player);
		
		connection.setContext(ServerContextRegistry.PLAY.get());
	}
}
