package muscaa.chess.client.gui.screens;

import com.badlogic.gdx.graphics.Color;
import com.kotcrab.vis.ui.widget.VisTable;

import muscaa.chess.client.assets.Sounds;
import muscaa.chess.client.gui.GuiScreen;
import muscaa.chess.client.gui.Widgets;
import muscaa.chess.client.render.WindowUtils;

public class MainScreen extends GuiScreen {
	
	@Override
	protected void init() {
		//Sounds.AMBIENT.loop(0.5F);
		
		VisTable main = Widgets.table(true);
		
		Widgets.label(main, Widgets.FONT_TITLE, "Chess", Color.WHITE);
		main.row();
		
		Widgets.button(main, "Offline", (button) -> {
			//ChessGame.INSTANCE.getBoardLayer().setBoard(new Board());
			setScreen(null);
			Sounds.AMBIENT.stop();
		}).getActor().setDisabled(true);
		main.row();
		Widgets.button(main, "Online", (button) -> setScreen(new OnlineScreen(this)));
		main.row();
		Widgets.button(main, "Options", (button) -> setScreen(new OptionsScreen(this)));
		main.row();
		Widgets.empty(main);
		main.row();
		Widgets.button(main, "Exit", (button) -> WindowUtils.exit());
		main.row();
		Widgets.empty(main, 100);
		main.row();
		
		stage.addActor(main);
	}
}
