package muscaa.chess.gui.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;

import muscaa.chess.ChessGame;
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
		
		Cell<VisLabel> cell = Widgets.label(main, Widgets.FONT_DEFAULT, text, Color.WHITE);
		cell.row();
		label = cell.getActor();
		
		Widgets.empty(main)
				.row();
		Widgets.button(main, "Return to Main Menu", (button) -> ChessGame.INSTANCE.returnToMainMenu())
				.row();
		
		stage.addActor(main);
	}
}
