package muscaa.chess.client.chat;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

import muscaa.chess.client.Client;
import muscaa.chess.client.layer.ILayer;
import muscaa.chess.client.layer.ILayerWrapper;
import muscaa.chess.client.layer.LayerUtils;
import muscaa.chess.client.utils.Screen;

public class ChatLayer implements ILayerWrapper {
	
	private final Client chess;
	private final Viewport viewport = Screen.VIEWPORT;
	private Stage stage;
	private ILayer stageLayer;
	private boolean opened = false;
	
	public ChatLayer(Client chess) {
		this.chess = chess;
	}
	
	public void init() {
		stage = new Stage(viewport);
		stageLayer = LayerUtils.fromGdx(stage);
	}
	
	@Override
	public void render(int mouseX, int mouseY, float delta) {
		stage.act(delta);
		stage.draw();
	}
	
	@Override
	public boolean hover(int mouseX, int mouseY) {
		return opened;
	}
	
	@Override
	public ILayer getWrappedLayer() {
		return stageLayer;
	}
	
	@Override
	public void dispose() {
		stage.dispose();
	}
}
