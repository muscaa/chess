package muscaa.chess.gui.screens;

import com.badlogic.gdx.graphics.Color;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;

import muscaa.chess.gui.GuiScreen;
import muscaa.chess.gui.Widgets;

public class StatusScreen extends GuiScreen {
	
	private final String text;
	
	public VisLabel label;
	
	public StatusScreen(String text) {
		this.text = text;
	}
	
	@Override
	protected void init() {
		VisTable main = Widgets.table(true);
		
		label = Widgets.label(main, Widgets.FONT_DEFAULT, text, Color.WHITE).getActor();
		
		stage.addActor(main);
	}
}