package muscaa.chess.client;

import com.badlogic.gdx.Input.Keys;

import muscaa.chess.client.gui.GuiScreen;
import muscaa.chess.client.gui.screens.PauseScreen;
import muscaa.chess.client.layer.ILayer;
import muscaa.chess.client.layer.LayerInputProcessor;

public class ClientInputProcessor extends LayerInputProcessor {
	
	private final Client chess;
	
	public ClientInputProcessor(Client chess) {
		this.chess = chess;
	}
	
	@Override
	protected ILayer getLayer() {
		return chess.layerManager;
	}
	
	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
			case Keys.F11:
				Client.INSTANCE.toggleFullscreen();
				return true;
				
			case Keys.ESCAPE:
				if (!chess.boardLayer.isInGame()) break;
				
				GuiScreen screen = Client.INSTANCE.getScreen();
				screen = screen == null ? new PauseScreen() : null;
				
				chess.setScreen(screen);
				
				return true;
		}
		
		return getLayer().keyUp(keycode);
	}
}
