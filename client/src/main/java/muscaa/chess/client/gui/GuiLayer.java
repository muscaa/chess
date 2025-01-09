package muscaa.chess.client.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.kotcrab.vis.ui.VisUI;

import muscaa.chess.client.Client;
import muscaa.chess.client.gui.screens.MainMenuScreen;
import muscaa.chess.client.layer.ILayer;
import muscaa.chess.client.layer.ILayerWrapper;
import muscaa.chess.client.layer.LayerUtils;
import muscaa.chess.client.registries.FontRegistry;
import muscaa.chess.client.utils.Screen;

public class GuiLayer implements ILayerWrapper {
	
    private final Skin skin;
    
    private GuiScreen screen;
	
	public GuiLayer() {
        skin = new Skin();
        VisUI.load(skin);
        
        skin.add(GuiScreen.FONT_DEFAULT, FontRegistry.VARELA_24.getBitmapFont(), BitmapFont.class);
        skin.add(GuiScreen.FONT_SMALL, FontRegistry.VARELA_18.getBitmapFont(), BitmapFont.class);
        skin.add(GuiScreen.FONT_TITLE, FontRegistry.MONTEZ_128.getBitmapFont(), BitmapFont.class);
        
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
			if (Client.INSTANCE.boardLayer.isInGame()) return;
			
			screen = new MainMenuScreen();
		}
		
		screen.init(Screen.VIEWPORT);
	}
	
	@Override
	public void dispose() {
		setScreen(null);
		VisUI.dispose();
	}
}
