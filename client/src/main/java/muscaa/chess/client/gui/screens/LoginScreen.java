package muscaa.chess.client.gui.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisValidatableTextField;

import muscaa.chess.client.Client;
import muscaa.chess.client.gui.GuiScreen;
import muscaa.chess.client.gui.Widgets;
import muscaa.chess.client.network.ChessClient;
import muscaa.chess.client.network.NetworkStatus;
import muscaa.chess.client.network.login.packets.CPacketLogin;

public class LoginScreen extends GuiScreen {
	
	private final GuiScreen continueScreen;
	private final ChessClient client;
	
	private VisValidatableTextField nameField;
	private VisValidatableTextField passwordField;
	private VisTextButton loginButton;
	
	public LoginScreen(ChessClient client, GuiScreen continueScreen) {
		this.client = client;
		this.continueScreen = continueScreen;
	}
	
	@Override
	protected void init() {
		VisTable main = Widgets.table(new VisTable(), true);
		main.defaults().growX().pad(PAD_LARGE).maxWidth(PANEL_MEDIUM);
		main.add(form());
		
		update();
		
		stage.addActor(main);
	}
	
	private void update() {
		loginButton.setDisabled(!nameField.isInputValid() || !passwordField.isInputValid());
	}
	
	private VisTable form() {
		VisTable main = Widgets.panel(new VisTable(), false);
		main.defaults().growX().pad(PAD_SMALL).height(BUTTON_HEIGHT);
		
		ChangeListener changeListener = new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				update();
			}
		};
		
		nameField = new VisValidatableTextField();
		nameField.setMessageText("Name");
		nameField.addListener(changeListener);
		nameField.addValidator(text -> !text.isBlank());
		main.add(nameField);
		main.row();
		
		passwordField = new VisValidatableTextField();
		passwordField.setMessageText("Password");
		passwordField.addListener(changeListener);
		passwordField.setPasswordMode(true);
		passwordField.addValidator(text -> !text.isBlank());
		main.add(passwordField);
		main.row();
		
		loginButton = Widgets.button(new VisTextButton("Login"), b -> {
			client.send(new CPacketLogin(nameField.getText(), passwordField.getText()));
			client.update(NetworkStatus.LOGIN);
			
			Client.INSTANCE.getGuiLayer().setScreen(continueScreen);
		});
		main.add(loginButton);
		main.row();
		
		VisTextButton cancelButton = Widgets.button(new VisTextButton("Return to Main Menu"), b -> Client.INSTANCE.returnToMainMenu());
		main.add(cancelButton);
		main.row();
		
		return main;
	}
}
