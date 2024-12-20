package muscaa.chess.client.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.kotcrab.vis.ui.VisUI;

import muscaa.chess.client.Core;
import muscaa.chess.client.assets.Fonts;
import muscaa.chess.client.gui.screens.MainScreen;
import muscaa.chess.client.layer.ILayer;
import muscaa.chess.client.layer.ILayerWrapper;
import muscaa.chess.client.layer.LayerUtils;
import muscaa.chess.client.render.Screen;

public class GuiLayer implements ILayerWrapper {
	
    private final Skin skin;
    
    private GuiScreen screen;
	
	public GuiLayer() {
        skin = new Skin();
        VisUI.load(skin);
        
        skin.add(Widgets.FONT_DEFAULT, Fonts.VARELA_24, BitmapFont.class);
        skin.add(Widgets.FONT_SMALL, Fonts.VARELA_18, BitmapFont.class);
        skin.add(Widgets.FONT_TITLE, Fonts.MONTEZ_128, BitmapFont.class);
        
        skin.addRegions(new TextureAtlas(Gdx.files.internal("ui/uiskin.atlas")));
        skin.load(Gdx.files.internal("ui/uiskin.json"));
	}
	
	@Override
	public boolean hover(int mouseX, int mouseY) {
		return screen != null;
	}
	
	@Override
	public ILayer getWrappedLayer() {
		return screen != null ? screen : LayerUtils.EMPTY;
	}
	
	public GuiScreen getScreen() {
		return screen;
	}
	
	public void setScreen(GuiScreen newScreen) {
		if (screen != null) {
			screen.dispose();
		}
		
		screen = newScreen;
		if (screen == null) {
			if (Core.INSTANCE.getBoardLayer().isInGame()) return;
			
			screen = new MainScreen();
		}
		
		screen.init(Screen.VIEWPORT);
	}
	
	@Override
	public void dispose() {
		setScreen(null);
		VisUI.dispose();
	}
}
