package muscaa.chess.client.gui.screens;

import muscaa.chess.client.gui.ChildGuiScreen;
import muscaa.chess.client.gui.GuiScreen;
import muscaa.chess.client.gui.widgets.WTable;
import muscaa.chess.client.gui.widgets.WTextButton;

public class OptionsScreen extends ChildGuiScreen {
	
	public OptionsScreen(GuiScreen parent) {
		super(parent);
	}
	
	@Override
	protected void init() {
		WTable content = new WTable();
		content.defaults().growX().pad(PAD_SMALL).minHeight(BUTTON_HEIGHT);
		
		WTextButton videoButton = new WTextButton("Video");
		videoButton.addActionListener(w -> chess.setScreen(new OptionsVideoScreen(this)));
		content.add(videoButton);
		content.row();
		
		WTextButton soundButton = new WTextButton("Sound");
		soundButton.addActionListener(w -> chess.setScreen(new OptionsSoundScreen(this)));
		content.add(soundButton);
		content.row();
		
		WTextButton themeButton = new WTextButton("Theme");
		themeButton.addActionListener(w -> chess.setScreen(parent));
		content.add(themeButton);
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
	
	@Override
	public void dispose() {
		chess.settingsConfig.save();
		
		super.dispose();
	}
}
