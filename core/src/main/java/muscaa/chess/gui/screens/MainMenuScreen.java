package muscaa.chess.gui.screens;

import com.kotcrab.vis.ui.widget.VisTable;

import muscaa.chess.gui.GuiScreen;
import muscaa.chess.gui.Widgets;
import muscaa.chess.render.WindowUtils;

public class MainMenuScreen extends GuiScreen {
	
	@Override
	protected void init() {
		VisTable table = new VisTable();
		table.defaults().pad(4.0F);
		table.setFillParent(true);
		
        Widgets.button(table::add, "Toggle Fullscreen", (button) -> WindowUtils.toggleFullscreen());
		Widgets.button(table::add, "Exit", (button) -> WindowUtils.exit());
		
		stage.addActor(table);
	}
}
