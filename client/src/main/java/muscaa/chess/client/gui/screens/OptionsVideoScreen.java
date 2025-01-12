package muscaa.chess.client.gui.screens;

import muscaa.chess.client.gui.ChildGuiScreen;
import muscaa.chess.client.gui.GuiScreen;
import muscaa.chess.client.gui.widgets.WTable;
import muscaa.chess.client.gui.widgets.WTextButton;

public class OptionsVideoScreen extends ChildGuiScreen {
	
	public OptionsVideoScreen(GuiScreen parent) {
		super(parent);
	}
	
	@Override
	protected void init() {
		WTable content = new WTable();
		content.defaults().growX().pad(PAD_SMALL).minHeight(BUTTON_HEIGHT);
		
		WTextButton fullscreenButton = new WTextButton("Toggle Fullscreen");
		fullscreenButton.addActionListener(w -> chess.settings.fullscreen.toggle());
		content.add(fullscreenButton);
		content.row();
		
		content.add();
		content.row();
		
		WTextButton backButton = new WTextButton("Back");
		backButton.addActionListener(w -> chess.setScreen(parent));
		content.add(backButton);
		content.row();
		
		WTable main = new WTable(true);
		main.defaults().growX().pad(PAD_LARGE).maxWidth(PANEL_MEDIUM);
		main.add(content);
		
		stage.addActor(main);
	}
}
