package muscaa.chess.client.gui.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Align;

import muscaa.chess.Chess;
import muscaa.chess.client.Client;
import muscaa.chess.client.assets.Fonts;
import muscaa.chess.client.gui.GuiScreen;
import muscaa.chess.client.gui.widgets.WLabel;
import muscaa.chess.client.gui.widgets.WTable;
import muscaa.chess.client.gui.widgets.WTextButton;
import muscaa.chess.client.registries.TextureRegistry;
import muscaa.chess.client.utils.WindowUtils;

public class MainMenuScreen extends GuiScreen {
	
	@Override
	protected void init() {
		WTable content = new WTable();
		content.defaults().growX().pad(PAD_SMALL).minHeight(BUTTON_HEIGHT);
		
		WLabel titleLabel = new WLabel("Chess", FONT_TITLE, Color.WHITE);
		titleLabel.setAlignment(Align.top);
		content.add(titleLabel).padBottom(PAD_LARGE * 3);
		content.row();
		
		WTable row1 = createRow();
		row1(row1);
		content.add(row1);
		content.row();
		
		WTable row2 = createRow();
		row2(row2);
		content.add(row2);
		content.row();
		
		WTable row3 = createRow();
		row3(row3);
		content.add(row3);
		content.row();
		
		WTable row4 = createRow();
		row4(row4);
		content.add(row4).padTop(PAD_MEDIUM * 2).padBottom(PAD_LARGE * 3);
		content.row();
		
		WTable main = new WTable(true);
		main.defaults().growX().maxWidth(PANEL_MEDIUM);
		main.add(content);
		
		stage.addActor(main);
	}
	
	private void row1(WTable row) {
		WTextButton offlineButton = new WTextButton("Offline");
		offlineButton.addActionListener(w -> Client.INSTANCE.getGuiLayer().setScreen(null));
		offlineButton.setDisabled(true);
		row.add(offlineButton);
	}
	
	private void row2(WTable row) {
		WTextButton onlineButton = new WTextButton("Online");
		onlineButton.addActionListener(w -> Client.INSTANCE.getGuiLayer().setScreen(new OnlineScreen(this)));
		row.add(onlineButton);
	}
	
	private void row3(WTable row) {
		WTextButton modsButton = new WTextButton("Mods");
		modsButton.addActionListener(w -> Client.INSTANCE.getGuiLayer().setScreen(new OptionsScreen(this)));
		row.add(modsButton);
		
		WTextButton optionsButton = new WTextButton("Options");
		optionsButton.addActionListener(w -> Client.INSTANCE.getGuiLayer().setScreen(new OptionsScreen(this)));
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
	public void render(int mouseX, int mouseY, float delta) {
		super.render(mouseX, mouseY, delta);
		
		Fonts.draw(Fonts.VARELA_18, "Version " + Chess.VERSION, 5, Fonts.VARELA_18.getLineHeight(), Color.WHITE);
	}
	
	@Override
	protected void renderBackground(int mouseX, int mouseY, float delta) {
		drawWallpaper(TextureRegistry.WALLPAPER);
	}
}
