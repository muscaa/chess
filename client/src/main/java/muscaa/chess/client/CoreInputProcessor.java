package muscaa.chess.client;

import com.badlogic.gdx.Input.Keys;

import muscaa.chess.client.gui.GuiScreen;
import muscaa.chess.client.gui.screens.PauseScreen;
import muscaa.chess.client.layer.ILayer;
import muscaa.chess.client.layer.LayerInputProcessor;
import muscaa.chess.client.render.WindowUtils;

public class CoreInputProcessor extends LayerInputProcessor {
	
	@Override
	protected ILayer getLayer() {
		return Core.INSTANCE.getLayerManager();
	}
	
	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
			case Keys.F11:
				WindowUtils.toggleFullscreen();
				return true;
				
			case Keys.ESCAPE:
				if (!Core.INSTANCE.getBoardLayer().isInGame()) break;
				
				GuiScreen screen = Core.INSTANCE.getGuiLayer().getScreen();
				screen = screen == null ? new PauseScreen() : null;
				
				Core.INSTANCE.getGuiLayer().setScreen(screen);
				
				return true;
		}
		
		return getLayer().keyUp(keycode);
	}
}
