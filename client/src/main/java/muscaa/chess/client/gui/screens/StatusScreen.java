package muscaa.chess.client.gui.screens;

import com.badlogic.gdx.graphics.Color;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;

import muscaa.chess.client.Client;
import muscaa.chess.client.gui.GuiScreen;
import muscaa.chess.client.gui.Widgets;

public class StatusScreen extends GuiScreen {
	
	private String text;
	private VisLabel label;
	
	public StatusScreen(String text) {
		this.text = text;
	}
	
	@Override
	protected void init() {
		VisTable main = Widgets.table(true);
		
		label = Widgets.label(main, Widgets.FONT_DEFAULT, text, Color.WHITE).getActor();
		main.row();
		
		Widgets.empty(main);
		main.row();
		Widgets.button(main, "Return to Main Menu", (button) -> Client.INSTANCE.returnToMainMenu());
		main.row();
		
		stage.addActor(main);
	}
	
	public void setText(String text) {
		this.text = text;
		label.setText(text);
	}
}
