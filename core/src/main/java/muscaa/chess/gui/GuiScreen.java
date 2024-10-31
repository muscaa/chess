package muscaa.chess.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

import muscaa.chess.ChessGame;
import muscaa.chess.layer.ILayer;
import muscaa.chess.layer.ILayerWrapper;
import muscaa.chess.layer.LayerUtils;
import muscaa.chess.render.Shapes;

public abstract class GuiScreen implements ILayerWrapper {
	
	protected Viewport viewport;
	protected Stage stage;
	private ILayer stageLayer;
	
	protected abstract void init();
	
	@Override
	public void render(int mouseX, int mouseY, float delta) {
		if (ChessGame.INSTANCE.getBoardLayer().getBoard() != null
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
		Shapes.rect(0, 0, viewport.getWorldWidth(), viewport.getWorldHeight(), new Color(0.0F, 0.0F, 0.5F, 0.5F));
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
}
