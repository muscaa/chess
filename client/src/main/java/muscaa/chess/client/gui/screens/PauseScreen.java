package muscaa.chess.client.gui.screens;

import com.kotcrab.vis.ui.widget.VisTable;

import muscaa.chess.client.Client;
import muscaa.chess.client.gui.GuiScreen;
import muscaa.chess.client.gui.Widgets;

public class PauseScreen extends GuiScreen {
	
	@Override
	protected void init() {
		VisTable main = Widgets.table(true);
		
		Widgets.button(main, "Resume", (button) -> Client.INSTANCE.getGuiLayer().setScreen(null));
		main.row();
		Widgets.button(main, "Options", (button) -> Client.INSTANCE.getGuiLayer().setScreen(new OptionsScreen(this)));
		main.row();
		
		if (Client.INSTANCE.getBoardLayer().isInGame()) {
			Widgets.button(main, "Surrender", null);
			main.row();
		}
		Widgets.empty(main);
		main.row();
		Widgets.button(main, "Return to Main Menu", (button) -> Client.INSTANCE.returnToMainMenu());
		main.row();
		
		stage.addActor(main);
	}
}
