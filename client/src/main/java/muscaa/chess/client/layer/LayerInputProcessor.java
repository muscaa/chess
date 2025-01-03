package muscaa.chess.client.layer;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

import muscaa.chess.client.utils.Screen;

public abstract class LayerInputProcessor implements InputProcessor {
	
	protected abstract ILayer getLayer();
	
	@Override
	public boolean keyDown(int keycode) {
		return getLayer().keyDown(keycode);
	}
	
	@Override
	public boolean keyUp(int keycode) {
		return getLayer().keyUp(keycode);
	}
	
	@Override
	public boolean keyTyped(char character) {
		return getLayer().keyTyped(character);
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector2 mouse = Screen.VIEWPORT.unproject(new Vector2(screenX, screenY));
		
		return getLayer().mouseDown((int) mouse.x, (int) mouse.y, pointer, button);
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		Vector2 mouse = Screen.VIEWPORT.unproject(new Vector2(screenX, screenY));
		
		return getLayer().mouseUp((int) mouse.x, (int) mouse.y, pointer, button);
	}
	
	@Override
	public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
		Vector2 mouse = Screen.VIEWPORT.unproject(new Vector2(screenX, screenY));
		
		return getLayer().mouseCancelled((int) mouse.x, (int) mouse.y, pointer, button);
	}
	
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		Vector2 mouse = Screen.VIEWPORT.unproject(new Vector2(screenX, screenY));
		
		return getLayer().mouseDragged((int) mouse.x, (int) mouse.y, pointer);
	}
	
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		Vector2 mouse = Screen.VIEWPORT.unproject(new Vector2(screenX, screenY));
		
		return getLayer().mouseMoved((int) mouse.x, (int) mouse.y);
	}
	
	@Override
	public boolean scrolled(float amountX, float amountY) {
		return getLayer().scrolled(amountX, amountY);
	}
}
