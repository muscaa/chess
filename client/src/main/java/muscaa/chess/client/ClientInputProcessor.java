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
		return Client.INSTANCE.getLayerManager();
	}
	
	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
			case Keys.F11:
				WindowUtils.toggleFullscreen();
				return true;
				
			case Keys.ESCAPE:
				if (!Client.INSTANCE.getBoardLayer().isInGame()) break;
				
				GuiScreen screen = Client.INSTANCE.getGuiLayer().getScreen();
				screen = screen == null ? new PauseScreen() : null;
				
				Client.INSTANCE.getGuiLayer().setScreen(screen);
				
				return true;
		}
		
		return getLayer().keyUp(keycode);
	}
}
