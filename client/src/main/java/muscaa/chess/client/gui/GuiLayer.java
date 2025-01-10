package muscaa.chess.client.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.kotcrab.vis.ui.VisUI;

import muscaa.chess.client.Client;
import muscaa.chess.client.assets.FontRegistry;
import muscaa.chess.client.layer.ILayer;
import muscaa.chess.client.layer.ILayerWrapper;
import muscaa.chess.client.layer.LayerUtils;

public class GuiLayer implements ILayerWrapper {
	
	private final Client chess;
    private final Skin skin;
    
	public GuiLayer(Client chess) {
		this.chess = chess;
		
        skin = new Skin();
        VisUI.load(skin);
        
        skin.add(GuiScreen.FONT_DEFAULT, FontRegistry.VARELA_24.get().getBitmapFont(), BitmapFont.class);
        skin.add(GuiScreen.FONT_SMALL, FontRegistry.VARELA_18.get().getBitmapFont(), BitmapFont.class);
        skin.add(GuiScreen.FONT_TITLE, FontRegistry.MONTEZ_128.get().getBitmapFont(), BitmapFont.class);
        
        skin.addRegions(new TextureAtlas(Gdx.files.internal("ui/uiskin.atlas")));
        skin.load(Gdx.files.internal("ui/uiskin.json"));
	}
	
	@Override
	public boolean hover(int mouseX, int mouseY) {
		return chess.getScreen() != null;
	}
	
	@Override
	public ILayer getWrappedLayer() {
		return chess.getScreen() != null ? chess.getScreen() : LayerUtils.EMPTY;
	}
	
	@Override
	public void dispose() {
		chess.setScreen(null);
		VisUI.dispose();
	}
}
