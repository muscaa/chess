package muscaa.chess.gui.screens;

import com.kotcrab.vis.ui.widget.VisTable;

import muscaa.chess.ChessGame;
import muscaa.chess.board.Board;
import muscaa.chess.gui.GuiScreen;
import muscaa.chess.gui.Widgets;
import muscaa.chess.render.WindowUtils;

public class MainMenuScreen extends GuiScreen {
	
	@Override
	protected void init() {
		VisTable main = Widgets.table(true);
		
		Widgets.button(main, "Offline", (button) -> {
			ChessGame.INSTANCE.getBoardLayer().setBoard(new Board());
			ChessGame.INSTANCE.getGuiLayer().setScreen(null);
		})
				.row();
		Widgets.button(main, "Online", null)
				.row();
		Widgets.button(main, "Options", null)
				.row();
		Widgets.empty(main)
				.row();
		Widgets.button(main, "Exit", (button) -> WindowUtils.exit())
				.row();
		
		stage.addActor(main);
	}
}
