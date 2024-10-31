package muscaa.chess.layer;

import com.badlogic.gdx.InputProcessor;

public class LayerUtils {
	
	public static final ILayer EMPTY = new ILayer() {};
	
	public static ILayer fromGdx(InputProcessor processor) {
		return new ILayer() {
			
			@Override
			public void render(int mouseX, int mouseY, float delta) {}
			
			@Override
			public boolean hover(int mouseX, int mouseY) {
				return true;
			}
			
			@Override
			public boolean keyDown(int keycode) {
				return processor.keyDown(keycode);
			}
			
			@Override
			public boolean keyUp(int keycode) {
				return processor.keyUp(keycode);
			}
			
			@Override
			public boolean keyTyped(char character) {
				return processor.keyTyped(character);
			}
			
			@Override
			public boolean mouseDown(int mouseX, int mouseY, int pointer, int button) {
				return processor.touchDown(mouseX, mouseY, pointer, button);
			}
			
			@Override
			public boolean mouseUp(int mouseX, int mouseY, int pointer, int button) {
				return processor.touchUp(mouseX, mouseY, pointer, button);
			}
			
			@Override
			public boolean mouseCancelled(int mouseX, int mouseY, int pointer, int button) {
				return processor.touchCancelled(mouseX, mouseY, pointer, button);
			}
			
			@Override
			public boolean mouseDragged(int mouseX, int mouseY, int pointer) {
				return processor.touchDragged(mouseX, mouseY, pointer);
			}
			
			@Override
			public boolean mouseMoved(int mouseX, int mouseY) {
				return processor.mouseMoved(mouseX, mouseY);
			}
			
			@Override
			public boolean scrolled(float amountX, float amountY) {
				return processor.scrolled(amountX, amountY);
			}
			
			@Override
			public void dispose() {}
		};
	}
	
	public static InputProcessor toGdx(ILayer layer) {
		return new InputProcessor() {
			
			@Override
			public boolean keyDown(int keycode) {
				return layer.keyDown(keycode);
			}
			
			@Override
			public boolean keyUp(int keycode) {
				return layer.keyUp(keycode);
			}
			
			@Override
			public boolean keyTyped(char character) {
				return layer.keyTyped(character);
			}
			
			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				return layer.mouseDown(screenX, screenY, pointer, button);
			}
			
			@Override
			public boolean touchUp(int screenX, int screenY, int pointer, int button) {
				return layer.mouseUp(screenX, screenY, pointer, button);
			}
			
			@Override
			public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
				return layer.mouseCancelled(screenX, screenY, pointer, button);
			}
			
			@Override
			public boolean touchDragged(int screenX, int screenY, int pointer) {
				return layer.mouseDragged(screenX, screenY, pointer);
			}
			
			@Override
			public boolean mouseMoved(int screenX, int screenY) {
				return layer.mouseMoved(screenX, screenY);
			}
			
			@Override
			public boolean scrolled(float amountX, float amountY) {
				return layer.scrolled(amountX, amountY);
			}
		};
	}
}
