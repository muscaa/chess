package muscaa.chess.client.gui.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Align;

import muscaa.chess.Chess;
import muscaa.chess.client.assets.FontRegistry;
import muscaa.chess.client.assets.TextureRegistry;
import muscaa.chess.client.board.LocalBoard;
import muscaa.chess.client.gui.GuiScreen;
import muscaa.chess.client.gui.widgets.WLabel;
import muscaa.chess.client.gui.widgets.WTable;
import muscaa.chess.client.gui.widgets.WTextButton;
import muscaa.chess.client.utils.WindowUtils;

public class MainMenuScreen extends GuiScreen {
	
	@Override
	protected void init() {
		WTable content = new WTable();
		content.defaults().growX().pad(PAD_SMALL).minHeight(BUTTON_HEIGHT);
		
		WLabel titleLabel = new WLabel("Chess", FONT_TITLE, Color.WHITE);
		titleLabel.setAlignment(Align.top);
		content.add(titleLabel);
		content.row();
		
		WTable rows = new WTable();
		rows.defaults().growX().pad(PAD_SMALL).minHeight(BUTTON_HEIGHT);
		
		WTable row1 = createRow();
		row1(row1);
		rows.add(row1);
		rows.row();
		
		WTable row2 = createRow();
		row2(row2);
		rows.add(row2);
		rows.row();
		
		WTable row3 = createRow();
		row3(row3);
		rows.add(row3);
		rows.row();
		
		WTable row4 = createRow();
		row4(row4);
		rows.add(row4).padTop(PAD_MEDIUM * 2);
		rows.row();
		
		content.add(rows).padTop(PAD_LARGE * 5).padBottom(PAD_LARGE * 5);
		content.row();
		
		WTable main = new WTable(true);
		main.defaults().growX().maxWidth(PANEL_MEDIUM);
		main.add(content);
		
		stage.addActor(main);
	}
	
	private void row1(WTable row) {
		WTextButton offlineButton = new WTextButton("Offline");
		offlineButton.addActionListener(w -> chess.setBoard(new LocalBoard()));
		row.add(offlineButton);
	}
	
	private void row2(WTable row) {
		WTextButton onlineButton = new WTextButton("Online");
		onlineButton.addActionListener(w -> chess.setScreen(new OnlineScreen(this)));
		row.add(onlineButton);
	}
	
	private void row3(WTable row) {
		WTextButton modsButton = new WTextButton("Mods");
		modsButton.addActionListener(w -> chess.setScreen(new ModsScreen(this)));
		row.add(modsButton);
		
		WTextButton optionsButton = new WTextButton("Options");
		optionsButton.addActionListener(w -> chess.setScreen(new OptionsScreen(this)));
		row.add(optionsButton);
	}
	
	private void row4(WTable row) {
		WTextButton exitButton = new WTextButton("Exit");
		exitButton.addActionListener(w -> WindowUtils.exit());
		row.add(exitButton);
	}
	
	private WTable createRow() {
		WTable row = new WTable();
		row.defaults().growX().pad(0, PAD_SMALL, 0, PAD_SMALL).minHeight(BUTTON_HEIGHT).colspan(2);
		return row;
	}
	
	@Override
	protected void renderBackground(int mouseX, int mouseY, float delta) {
		drawWallpaper(TextureRegistry.WALLPAPER.get());
		
		FontRegistry.VARELA_18.get().draw("Version " + Chess.VERSION, 5, FontRegistry.VARELA_18.get().getHeight(), Color.WHITE);
	}
}
