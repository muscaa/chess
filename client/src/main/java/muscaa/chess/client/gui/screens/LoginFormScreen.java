package muscaa.chess.client.gui.screens;

import muscaa.chess.client.Client;
import muscaa.chess.client.gui.GuiScreen;
import muscaa.chess.client.gui.widgets.WPanel;
import muscaa.chess.client.gui.widgets.WTable;
import muscaa.chess.client.gui.widgets.WTextButton;
import muscaa.chess.client.gui.widgets.WTextField;
import muscaa.chess.client.network.ChessClient;
import muscaa.chess.client.network.NetworkStatus;
import muscaa.chess.client.network.login.packets.CPacketLogin;

public class LoginFormScreen extends GuiScreen {
	
	private final GuiScreen continueScreen;
	private final ChessClient client;
	
	private WTextField nameField;
	private WTextField passwordField;
	private WTextButton loginButton;
	
	public LoginFormScreen(ChessClient client, GuiScreen continueScreen) {
		this.client = client;
		this.continueScreen = continueScreen;
	}
	
	@Override
	protected void init() {
		WTable main = new WTable(true);
		main.defaults().growX().pad(PAD_LARGE).maxWidth(PANEL_MEDIUM);
		main.add(form());
		
		update();
		
		stage.addActor(main);
	}
	
	private void update() {
		loginButton.setDisabled(!nameField.isInputValid() || !passwordField.isInputValid());
	}
	
	private WPanel form() {
		WTable content = new WTable();
		content.defaults().growX().pad(PAD_SMALL).minHeight(BUTTON_HEIGHT);
		
		nameField = new WTextField();
		nameField.setMessageText("Name");
		nameField.addActionListener(w -> update());
		nameField.addValidator(text -> !text.isBlank());
		content.add(nameField);
		content.row();
		
		passwordField = new WTextField();
		passwordField.setMessageText("Password");
		passwordField.addActionListener(w -> update());
		passwordField.setPasswordMode(true);
		passwordField.addValidator(text -> !text.isBlank());
		content.add(passwordField);
		content.row();
		
		content.add();
		content.row();
		
		loginButton = new WTextButton("Login");
		loginButton.addActionListener(w -> {
			client.send(new CPacketLogin(nameField.getText(), passwordField.getText()));
			client.update(NetworkStatus.LOGIN);
			
			chess.setScreen(continueScreen);
		});
		content.add(loginButton);
		content.row();
		
		WTextButton mainMenuButton = new WTextButton("Return to Main Menu");
		mainMenuButton.addActionListener(w -> Client.INSTANCE.returnToMainMenu());
		content.add(mainMenuButton);
		content.row();
		
		WPanel form = new WPanel();
		form.defaults().growX().pad(PAD_MEDIUM);
		form.add(content);
		
		return form;
	}
}
