package muscaa.chess.gui.screens;

import com.kotcrab.vis.ui.widget.VisTable;

import muscaa.chess.ChessGame;
import muscaa.chess.gui.GuiScreen;
import muscaa.chess.gui.Widgets;

public class PauseScreen extends GuiScreen {
	
	@Override
	protected void init() {
		VisTable main = Widgets.table(true);
		
		Widgets.button(main, "Resume", (button) -> setScreen(null))
				.row();
		Widgets.button(main, "Options", (button) -> setScreen(new OptionsScreen(this)))
				.row();
		Widgets.button(main, "Surrender", null)
				.row();
		Widgets.empty(main)
				.row();
		Widgets.button(main, "Return to Main Menu", (button) -> {
			ChessGame.INSTANCE.getBoardLayer().setBoard(null);
			setScreen(new MainScreen());
		})
				.row();
		
		stage.addActor(main);
	}
}
