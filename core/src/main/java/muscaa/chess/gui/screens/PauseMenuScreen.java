package muscaa.chess.gui.screens;

import com.kotcrab.vis.ui.widget.VisTable;

import muscaa.chess.ChessGame;
import muscaa.chess.gui.GuiScreen;
import muscaa.chess.gui.Widgets;

public class PauseMenuScreen extends GuiScreen {
	
	@Override
	protected void init() {
		VisTable main = Widgets.table(true);
		
		Widgets.button(main, "Options", null)
				.row();
		Widgets.empty(main)
				.row();
		Widgets.button(main, "Surrender", null)
				.row();
		Widgets.button(main, "Return to Main Menu", (button) -> {
			ChessGame.INSTANCE.getBoardLayer().setBoard(null);
			ChessGame.INSTANCE.getGuiLayer().setScreen(new MainMenuScreen());
		})
				.row();
		
		stage.addActor(main);
	}
}
