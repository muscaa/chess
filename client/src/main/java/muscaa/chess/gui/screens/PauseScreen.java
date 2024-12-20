package muscaa.chess.gui.screens;

import com.kotcrab.vis.ui.widget.VisTable;

import muscaa.chess.Core;
import muscaa.chess.gui.GuiScreen;
import muscaa.chess.gui.Widgets;

public class PauseScreen extends GuiScreen {
	
	@Override
	protected void init() {
		VisTable main = Widgets.table(true);
		
		Widgets.button(main, "Resume", (button) -> setScreen(null));
		main.row();
		Widgets.button(main, "Options", (button) -> setScreen(new OptionsScreen(this)));
		main.row();
		
		if (Core.INSTANCE.getBoardLayer().isInGame()) {
			Widgets.button(main, "Surrender", null);
			main.row();
		}
		Widgets.empty(main);
		main.row();
		Widgets.button(main, "Return to Main Menu", (button) -> Core.INSTANCE.returnToMainMenu());
		main.row();
		
		stage.addActor(main);
	}
}
