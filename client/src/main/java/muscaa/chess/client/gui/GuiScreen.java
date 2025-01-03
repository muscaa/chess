package muscaa.chess.client.gui;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

import muscaa.chess.client.Client;
import muscaa.chess.client.config.Theme;
import muscaa.chess.client.layer.ILayer;
import muscaa.chess.client.layer.ILayerWrapper;
import muscaa.chess.client.layer.LayerUtils;
import muscaa.chess.client.registries.TextureRegistry;
import muscaa.chess.client.utils.Shapes;

public abstract class GuiScreen implements ILayerWrapper {
	
	public static final String FONT_DEFAULT = "default-font";
	public static final String FONT_SMALL = "small-font";
	public static final String FONT_TITLE = "title-font";
	
	public static final int BUTTON_HEIGHT = 50;
	
	public static final int PANEL_LARGE = 1000;
	public static final int PANEL_MEDIUM = 500;
	public static final int PANEL_SMALL = 200;
	
	public static final int PAD_LARGE = 50;
	public static final int PAD_MEDIUM = 10;
	public static final int PAD_SMALL = 4;
	
	protected Viewport viewport;
	protected Stage stage;
	private ILayer stageLayer;
	
	protected abstract void init();
	
	@Override
	public void render(int mouseX, int mouseY, float delta) {
		renderBackground(mouseX, mouseY, delta);
		
        stage.act(delta);
        stage.draw();
	}
	
	@Override
	public ILayer getWrappedLayer() {
		return stageLayer;
	}
	
	protected void renderBackground(int mouseX, int mouseY, float delta) {
		if (Client.INSTANCE.getBoardLayer().isInGame()) {
			Shapes.rect(0, 0, viewport.getWorldWidth(), viewport.getWorldHeight(), Theme.PANEL);
		} else {
			TextureRegistry.WALLPAPER_BLUR.draw(0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
		}
	}
	
	void init(Viewport viewport) {
		this.viewport = viewport;
		
		stage = new Stage(viewport);
		stageLayer = LayerUtils.fromGdx(stage);
		
		init();
	}
	
	@Override
	public void dispose() {
		stage.dispose();
	}
}
