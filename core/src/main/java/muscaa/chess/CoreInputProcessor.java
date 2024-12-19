package muscaa.chess;

import com.badlogic.gdx.Input.Keys;

import muscaa.chess.gui.GuiScreen;
import muscaa.chess.gui.screens.PauseScreen;
import muscaa.chess.layer.ILayer;
import muscaa.chess.layer.LayerInputProcessor;
import muscaa.chess.render.WindowUtils;

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