package muscaa.chess;

import com.badlogic.gdx.Input.Keys;

import muscaa.chess.gui.GuiScreen;
import muscaa.chess.gui.screens.PauseMenuScreen;

import com.badlogic.gdx.InputProcessor;

public class ChessInputProcessor implements InputProcessor {
	
	@Override
	public boolean keyDown(int keycode) {
		return ChessGame.INSTANCE.getLayerManager().keyDown(keycode);
	}
	
	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.ESCAPE) {
			GuiScreen screen = ChessGame.INSTANCE.getGuiLayer().getScreen();
			if (screen == null) {
				screen = new PauseMenuScreen();
			} else {
				screen = null;
			}
			
			ChessGame.INSTANCE.getGuiLayer().setScreen(screen);
			
			return true;
		}
		
		return ChessGame.INSTANCE.getLayerManager().keyUp(keycode);
	}
	
	@Override
	public boolean keyTyped(char character) {
		return ChessGame.INSTANCE.getLayerManager().keyTyped(character);
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return ChessGame.INSTANCE.getLayerManager().mouseDown(screenX, screenY, pointer, button);
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return ChessGame.INSTANCE.getLayerManager().mouseUp(screenX, screenY, pointer, button);
	}
	
	@Override
	public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
		return ChessGame.INSTANCE.getLayerManager().mouseCancelled(screenX, screenY, pointer, button);
	}
	
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return ChessGame.INSTANCE.getLayerManager().mouseDragged(screenX, screenY, pointer);
	}
	
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return ChessGame.INSTANCE.getLayerManager().mouseMoved(screenX, screenY);
	}
	
	@Override
	public boolean scrolled(float amountX, float amountY) {
		return ChessGame.INSTANCE.getLayerManager().scrolled(amountX, amountY);
	}
}
