package muscaa.chess.network.login;

import fluff.network.NetworkException;
import fluff.network.client.IClient;
import muscaa.chess.AbstractServer;
import muscaa.chess.form.Form;
import muscaa.chess.form.FormData;
import muscaa.chess.form.field.FormField;
import muscaa.chess.form.field.FormFieldData;
import muscaa.chess.form.field.FormFieldRegistry;
import muscaa.chess.network.DisconnectReasonRegistry;
import muscaa.chess.network.ServerContextRegistry;
import muscaa.chess.network.base.ServerBaseNetHandler;
import muscaa.chess.network.login.packets.SPacketLogin;
import muscaa.chess.network.login.packets.SPacketLoginForm;
import muscaa.chess.network.login.packets.SPacketProfile;
import muscaa.chess.player.players.RemoteServerPlayer;

public class ServerLoginNetHandler extends ServerBaseNetHandler implements IServerLoginNetHandler {
	
	public static final Form DEFAULT_LOGIN_FORM;
	static {
		DEFAULT_LOGIN_FORM = new Form("login", "Login", "Login");
		DEFAULT_LOGIN_FORM.add(new FormField("name", FormFieldRegistry.STRING.get(), "Name"));
	}
	
	protected final AbstractServer gameServer;
	protected final Form loginForm;
	
	public ServerLoginNetHandler(AbstractServer gameServer) {
		this.gameServer = gameServer;
		
		loginForm = buildLoginForm();
	}
	
	protected Form buildLoginForm() {
		return DEFAULT_LOGIN_FORM;
	}
	
	protected void handleLoginData(FormData loginData) {
		FormField nameField = loginForm.get("name");
		FormFieldData nameFieldData = loginData.get("name");
		
		String name = nameField.get(nameFieldData);
		
		connection.login(name);
		
		connection.player = new RemoteServerPlayer(connection);
		connection.player.setName(name);
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
			connection.disconnect(DisconnectReasonRegistry.KICK.get(), "Invalid login form data!");
			return;
		}
		
		handleLoginData(loginData);
	}
	
	@Override
	public void onConnect() throws NetworkException {
		connection.send(new SPacketProfile(connection.player.getName()));
		gameServer.addPlayer(connection.player);
		
		connection.setContext(ServerContextRegistry.PLAY.get());
	}
}
