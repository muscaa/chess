package muscaa.chess.client.gui;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

import muscaa.chess.client.Core;
import muscaa.chess.client.config.Theme;
import muscaa.chess.client.layer.ILayer;
import muscaa.chess.client.layer.ILayerWrapper;
import muscaa.chess.client.layer.LayerUtils;
import muscaa.chess.client.render.Shapes;

public abstract class GuiScreen implements ILayerWrapper {
	
	protected Viewport viewport;
	protected Stage stage;
	private ILayer stageLayer;
	
	protected abstract void init();
	
	@Override
	public void render(int mouseX, int mouseY, float delta) {
		if (Core.INSTANCE.getBoardLayer().isInGame()
				|| forceRenderBackground()) {
			renderBackground(mouseX, mouseY, delta);
		}
		
        stage.act(delta);
        stage.draw();
	}
	
	@Override
	public ILayer getWrappedLayer() {
		return stageLayer;
	}
	
	protected void renderBackground(int mouseX, int mouseY, float delta) {
		Shapes.rect(0, 0, viewport.getWorldWidth(), viewport.getWorldHeight(), Theme.GUISCREEN_BACKGROUND);
	}
	
	protected boolean forceRenderBackground() {
		return false;
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
	
	public static void setScreen(GuiScreen screen) {
		Core.INSTANCE.getGuiLayer().setScreen(screen);
	}
}
