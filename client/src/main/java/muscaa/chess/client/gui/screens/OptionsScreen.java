package muscaa.chess.client.gui.screens;

import muscaa.chess.client.gui.ChildGuiScreen;
import muscaa.chess.client.gui.GuiScreen;
import muscaa.chess.client.gui.widgets.WTable;

public class OptionsScreen extends ChildGuiScreen {
	
	public OptionsScreen(GuiScreen parent) {
		super(parent);
	}
	
	@Override
	protected void init() {
		WTable main = new WTable(true);
		
		stage.addActor(main);
	}
}
