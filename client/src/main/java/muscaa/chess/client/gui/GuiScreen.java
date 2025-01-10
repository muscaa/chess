package muscaa.chess.client.gui;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

import muscaa.chess.client.Client;
import muscaa.chess.client.assets.TextureRegistry;
import muscaa.chess.client.assets.TextureValue;
import muscaa.chess.client.config.Theme;
import muscaa.chess.client.layer.ILayer;
import muscaa.chess.client.layer.ILayerWrapper;
import muscaa.chess.client.layer.LayerUtils;
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
	public static final int PAD_MEDIUM = 20;
	public static final int PAD_SMALL = 4;
	
	protected Client chess;
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
		if (chess.isInGame()) {
			Shapes.rect(0, 0, viewport.getWorldWidth(), viewport.getWorldHeight(), Theme.GUISCREEN_BACKGROUND);
		} else {
			drawWallpaper(TextureRegistry.WALLPAPER_BLUR.get());
		}
	}
	
	protected void drawWallpaper(TextureValue texture) {
		float viewportWidth = viewport.getWorldWidth();
		float viewportHeight = viewport.getWorldHeight();
		
		float viewportAspect = viewportWidth / viewportHeight;
		float textureAspect = (float) texture.getWidth() / (float) texture.getHeight();
		
		int width;
		int height;
		
		if (viewportAspect < textureAspect) {
			height = (int) viewportHeight;
			width = (int) (height * textureAspect);
		} else {
			width = (int) viewportWidth;
			height = (int) (width / textureAspect);
		}
		
		int x = (int) (viewportWidth - width);
		int y = (int) (viewportHeight - height) / 2;
		
		texture.draw(x, y, width, height);
	}
	
	public void init(Client chess, Viewport viewport) {
		this.chess = chess;
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
