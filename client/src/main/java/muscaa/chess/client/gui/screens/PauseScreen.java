package muscaa.chess.client.gui.screens;

import muscaa.chess.client.gui.GuiScreen;
import muscaa.chess.client.gui.widgets.WTable;
import muscaa.chess.client.gui.widgets.WTextButton;

public class PauseScreen extends GuiScreen {
	
	@Override
	protected void init() {
		WTable content = new WTable();
		content.defaults().growX().pad(PAD_SMALL).minHeight(BUTTON_HEIGHT);
		
		WTextButton resumeButton = new WTextButton("Resume");
		resumeButton.addActionListener(w -> chess.setScreen(null));
		content.add(resumeButton);
		content.row();
		
		WTextButton optionsButton = new WTextButton("Options");
		optionsButton.addActionListener(w -> chess.setScreen(new OptionsScreen(this)));
		content.add(optionsButton);
		content.row();
		
		if (chess.isInGame()) {
			WTextButton surrenderButton = new WTextButton("Surrender");
			surrenderButton.addActionListener(w -> {
				
			});
			content.add(surrenderButton);
			content.row();
		}
		
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
