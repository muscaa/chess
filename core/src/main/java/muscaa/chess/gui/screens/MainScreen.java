package muscaa.chess.gui.screens;

import com.badlogic.gdx.graphics.Color;
import com.kotcrab.vis.ui.widget.VisTable;

import muscaa.chess.assets.Sounds;
import muscaa.chess.gui.GuiScreen;
import muscaa.chess.gui.Widgets;
import muscaa.chess.render.WindowUtils;

public class MainScreen extends GuiScreen {
	
	@Override
	protected void init() {
		//Sounds.AMBIENT.loop(0.5F);
		
		VisTable main = Widgets.table(true);
		
		Widgets.label(main, Widgets.FONT_TITLE, "Chess", Color.WHITE)
				.row();
		
		Widgets.button(main, "Offline", (button) -> {
			//ChessGame.INSTANCE.getBoardLayer().setBoard(new Board());
			setScreen(null);
			Sounds.AMBIENT.stop();
		})
				.row();
		Widgets.button(main, "Online", (button) -> setScreen(new OnlineScreen(this)))
				.row();
		Widgets.button(main, "Options", (button) -> setScreen(new OptionsScreen(this)))
				.row();
		Widgets.empty(main)
				.row();
		Widgets.button(main, "Exit", (button) -> WindowUtils.exit())
				.row();
		Widgets.empty(main, 100)
				.row();
		
		stage.addActor(main);
	}
}
