package muscaa.chess.client.gui.screens;

import com.badlogic.gdx.utils.Align;

import muscaa.chess.client.gui.GuiScreen;
import muscaa.chess.client.gui.widgets.WLabel;
import muscaa.chess.client.gui.widgets.WTable;
import muscaa.chess.client.gui.widgets.WTextButton;

public class DisconnectedScreen extends GuiScreen {
	
	private final String message;
	
	public DisconnectedScreen(String message) {
		this.message = message == null ? "Disconnected" : message;
	}
	
	@Override
	protected void init() {
		WTable content = new WTable();
		content.defaults().growX().pad(PAD_SMALL).minHeight(BUTTON_HEIGHT);
		
		WLabel messageLabel = new WLabel(message);
		messageLabel.setAlignment(Align.center);
		messageLabel.setWrap(true);
		content.add(messageLabel);
		content.row();
		
		content.add();
		content.row();
		
		WTextButton mainMenuButton = new WTextButton("Return to Main Menu");
		mainMenuButton.addActionListener(w -> chess.returnToMainMenu());
		content.add(mainMenuButton);
		content.row();
		
		WTable main = new WTable(true);
		main.defaults().growX().pad(PAD_LARGE).maxWidth(PANEL_MEDIUM);
		main.add(content);
		
		stage.addActor(main);
	}
}
