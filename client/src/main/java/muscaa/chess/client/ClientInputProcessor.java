package muscaa.chess.client;

import com.badlogic.gdx.Input.Keys;

import muscaa.chess.client.gui.GuiScreen;
import muscaa.chess.client.gui.screens.PauseScreen;
import muscaa.chess.client.layer.ILayer;
import muscaa.chess.client.layer.LayerInputProcessor;
import muscaa.chess.client.utils.WindowUtils;

public class ClientInputProcessor extends LayerInputProcessor {
	
	@Override
	protected ILayer getLayer() {
		return Client.INSTANCE.layerManager;
	}
	
	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
			case Keys.F11:
				WindowUtils.toggleFullscreen();
				return true;
				
			case Keys.ESCAPE:
				if (!Client.INSTANCE.boardLayer.isInGame()) break;
				
				GuiScreen screen = Client.INSTANCE.guiLayer.getScreen();
				screen = screen == null ? new PauseScreen() : null;
				
				Client.INSTANCE.guiLayer.setScreen(screen);
				
				return true;
		}
		
		return getLayer().keyUp(keycode);
	}
}
