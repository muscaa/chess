package muscaa.chess.client.layer;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

import muscaa.chess.client.utils.Screen;

public abstract class GdxLayer implements ILayer {
	
	protected abstract InputProcessor getProcessor();
	
	@Override
	public void render(int mouseX, int mouseY, float delta) {
		ILayer.super.render(mouseX, mouseY, delta);
	}
	
	@Override
	public void resize(int width, int height) {
		ILayer.super.resize(width, height);
	}
	
	@Override
	public boolean hover(int mouseX, int mouseY) {
		return ILayer.super.hover(mouseX, mouseY);
	}
	
	@Override
	public boolean keyDown(int keycode) {
		return getProcessor().keyDown(keycode);
	}
	
	@Override
	public boolean keyUp(int keycode) {
		return getProcessor().keyUp(keycode);
	}
	
	@Override
	public boolean keyTyped(char character) {
		return getProcessor().keyTyped(character);
	}
	
	@Override
	public boolean mouseDown(int mouseX, int mouseY, int pointer, int button) {
		Vector2 screen = Screen.VIEWPORT.project(new Vector2(mouseX, Screen.HEIGHT - mouseY - 1));
		
		return getProcessor().touchDown((int) screen.x, (int) screen.y, pointer, button);
	}
	
	@Override
	public boolean mouseUp(int mouseX, int mouseY, int pointer, int button) {
		Vector2 screen = Screen.VIEWPORT.project(new Vector2(mouseX, Screen.HEIGHT - mouseY - 1));
		
		return getProcessor().touchUp((int) screen.x, (int) screen.y, pointer, button);
	}
	
	@Override
	public boolean mouseCancelled(int mouseX, int mouseY, int pointer, int button) {
		Vector2 screen = Screen.VIEWPORT.project(new Vector2(mouseX, Screen.HEIGHT - mouseY - 1));
		
		return getProcessor().touchCancelled((int) screen.x, (int) screen.y, pointer, button);
	}
	
	@Override
	public boolean mouseDragged(int mouseX, int mouseY, int pointer) {
		Vector2 screen = Screen.VIEWPORT.project(new Vector2(mouseX, Screen.HEIGHT - mouseY - 1));
		
		return getProcessor().touchDragged((int) screen.x, (int) screen.y, pointer);
	}
	
	@Override
	public boolean mouseMoved(int mouseX, int mouseY) {
		Vector2 screen = Screen.VIEWPORT.project(new Vector2(mouseX, Screen.HEIGHT - mouseY - 1));
		
		return getProcessor().mouseMoved((int) screen.x, (int) screen.y);
	}
	
	@Override
	public boolean scrolled(float amountX, float amountY) {
		return getProcessor().scrolled(amountX, amountY);
	}
	
	@Override
	public void dispose() {
		ILayer.super.dispose();
	}
}
