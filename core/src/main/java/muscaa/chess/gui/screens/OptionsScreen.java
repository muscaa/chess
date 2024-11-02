package muscaa.chess.gui.screens;

import com.kotcrab.vis.ui.widget.VisTable;

import muscaa.chess.gui.GuiScreen;
import muscaa.chess.gui.Widgets;

public class OptionsScreen extends GuiScreen {
	
	private final GuiScreen parent;
	
	public OptionsScreen(GuiScreen parent) {
		this.parent = parent;
	}
	
	@Override
	protected void init() {
		VisTable main = Widgets.table(true);
		
		Widgets.button(main, "Sound", null)
				.row();
		Widgets.slider(main, 0, 10, 1, 5, null)
				.row();
		Widgets.checkbox(main, "Checkbox Text", true, null)
				.row();
		Widgets.empty(main)
				.row();
		Widgets.button(main, "Back", (button) -> setScreen(parent))
				.row();
		
		stage.addActor(main);
	}
}
