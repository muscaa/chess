package muscaa.chess.client.gui.screens;

import com.kotcrab.vis.ui.widget.VisTable;

import muscaa.chess.client.Client;
import muscaa.chess.client.gui.GuiScreen;
import muscaa.chess.client.gui.Widgets;

public class OptionsScreen extends GuiScreen {
	
	private final GuiScreen parent;
	
	public OptionsScreen(GuiScreen parent) {
		this.parent = parent;
	}
	
	@Override
	protected void init() {
		VisTable main = Widgets.table(true);
		
		Widgets.button(main, "Sound", null);
		main.row();
		Widgets.slider(main, 0, 10, 1, 5, null);
		main.row();
		Widgets.checkbox(main, "Checkbox Text", true, null);
		main.row();
		Widgets.empty(main);
		main.row();
		Widgets.button(main, "Back", (button) -> Client.INSTANCE.getGuiLayer().setScreen(parent));
		main.row();
		
		stage.addActor(main);
	}
}
