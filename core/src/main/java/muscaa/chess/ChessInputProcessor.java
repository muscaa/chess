package muscaa.chess;

import com.badlogic.gdx.Input.Keys;

import muscaa.chess.gui.GuiScreen;
import muscaa.chess.gui.screens.PauseScreen;
import muscaa.chess.layer.ILayer;
import muscaa.chess.layer.LayerInputProcessor;
import muscaa.chess.render.WindowUtils;

public class ChessInputProcessor extends LayerInputProcessor {
	
	@Override
	protected ILayer getLayer() {
		return ChessGame.INSTANCE.getLayerManager();
	}
	
	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
			case Keys.F11:
				WindowUtils.toggleFullscreen();
				return true;
				
			case Keys.ESCAPE:
				if (ChessGame.INSTANCE.getBoardLayer().getBoard() == null) break;
				
				GuiScreen screen = ChessGame.INSTANCE.getGuiLayer().getScreen();
				screen = screen == null ? new PauseScreen() : null;
				
				ChessGame.INSTANCE.getGuiLayer().setScreen(screen);
				
				return true;
		}
		
		return getLayer().keyUp(keycode);
	}
}
