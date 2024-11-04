package muscaa.chess.gui.screens;

import com.badlogic.gdx.graphics.Color;
import com.kotcrab.vis.ui.widget.VisTable;

import muscaa.chess.ChessGame;
import muscaa.chess.gui.GuiScreen;
import muscaa.chess.gui.Widgets;

public class DisconnectedScreen extends GuiScreen {
	
	private final String message;
	
	public DisconnectedScreen(String message) {
		this.message = message;
	}
	
	@Override
	protected void init() {
		VisTable main = Widgets.table(true);
		
		Widgets.label(main, Widgets.FONT_DEFAULT, message, Color.WHITE)
				.row();
		Widgets.empty(main)
				.row();
		Widgets.button(main, "Return to Main Menu", (button) -> ChessGame.INSTANCE.returnToMainMenu())
				.row();
		
		stage.addActor(main);
	}
}
