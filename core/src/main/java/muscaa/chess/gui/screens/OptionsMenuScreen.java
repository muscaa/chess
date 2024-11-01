package muscaa.chess.gui.screens;

import com.kotcrab.vis.ui.widget.VisTable;

import muscaa.chess.ChessGame;
import muscaa.chess.gui.GuiScreen;
import muscaa.chess.gui.Widgets;

public class OptionsMenuScreen extends GuiScreen {
	
	private final GuiScreen back;
	
	public OptionsMenuScreen(GuiScreen back) {
		this.back = back;
	}
	
	@Override
	protected void init() {
		VisTable main = Widgets.table(true);
		
		Widgets.button(main, "Sound", null)
				.row();
		Widgets.empty(main)
				.row();
		Widgets.button(main, "Back", (button) -> ChessGame.INSTANCE.getGuiLayer().setScreen(back))
				.row();
		
		stage.addActor(main);
	}
}
