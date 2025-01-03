package muscaa.chess.client.gui.screens;

import com.badlogic.gdx.graphics.Color;
import com.kotcrab.vis.ui.widget.VisTable;

import muscaa.chess.client.Client;
import muscaa.chess.client.gui.GuiScreen;
import muscaa.chess.client.gui.Widgets;

public class DisconnectedScreen extends GuiScreen {
	
	private final String message;
	
	public DisconnectedScreen(String message) {
		this.message = message;
	}
	
	@Override
	protected void init() {
		VisTable main = Widgets.table(true);
		
		Widgets.label(main, Widgets.FONT_DEFAULT, message, Color.WHITE);
		main.row();
		Widgets.empty(main);
		main.row();
		Widgets.button(main, "Return to Main Menu", (button) -> Client.INSTANCE.returnToMainMenu());
		main.row();
		
		stage.addActor(main);
	}
}
