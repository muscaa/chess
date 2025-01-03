package muscaa.chess.client.gui.screens;

import com.badlogic.gdx.graphics.Color;
import com.kotcrab.vis.ui.widget.VisTable;

import muscaa.chess.Chess;
import muscaa.chess.client.Client;
import muscaa.chess.client.assets.Fonts;
import muscaa.chess.client.assets.Sounds;
import muscaa.chess.client.gui.GuiScreen;
import muscaa.chess.client.gui.Widgets;
import muscaa.chess.client.registries.TextureRegistry;
import muscaa.chess.client.utils.WindowUtils;

public class MainMenuScreen extends GuiScreen {
	
	@Override
	protected void init() {
		//Sounds.AMBIENT.loop(0.5F);
		
		VisTable main = Widgets.table(true);
		
		Widgets.label(main, Widgets.FONT_TITLE, "Chess", Color.WHITE);
		main.row();
		
		Widgets.button(main, "Offline", (button) -> {
			//ChessGame.INSTANCE.getBoardLayer().setBoard(new Board());
			Client.INSTANCE.getGuiLayer().setScreen(null);
			Sounds.AMBIENT.stop();
		}).getActor().setDisabled(true);
		main.row();
		Widgets.button(main, "Online", (button) -> Client.INSTANCE.getGuiLayer().setScreen(new OnlineScreen(this)));
		main.row();
		Widgets.button(main, "Options", (button) -> Client.INSTANCE.getGuiLayer().setScreen(new OptionsScreen(this)));
		main.row();
		Widgets.empty(main);
		main.row();
		Widgets.button(main, "Exit", (button) -> WindowUtils.exit());
		main.row();
		Widgets.empty(main, 100);
		main.row();
		
		stage.addActor(main);
	}
	
	@Override
	public void render(int mouseX, int mouseY, float delta) {
		super.render(mouseX, mouseY, delta);
		
		Fonts.draw(Fonts.VARELA_18, "Version " + Chess.VERSION, 5, Fonts.VARELA_18.getLineHeight(), Color.WHITE);
	}
	
	@Override
	protected void renderBackground(int mouseX, int mouseY, float delta) {
		TextureRegistry.WALLPAPER.draw(0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
	}
}
