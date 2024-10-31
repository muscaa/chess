package muscaa.chess.layer;

import com.badlogic.gdx.utils.Disposable;

public interface ILayer extends Disposable {
	
	default void render(int mouseX, int mouseY, float delta) {}
	
	default void resize(int width, int height) {}
	
	default boolean hover(int mouseX, int mouseY) {
		return false;
	}
	
	default boolean keyDown(int keycode) {
		return false;
	}
	
	default boolean keyUp(int keycode) {
		return false;
	}
	
	default boolean keyTyped(char character) {
		return false;
	}
	
	default boolean mouseDown(int mouseX, int mouseY, int pointer, int button) {
		return false;
	}
	
	default boolean mouseUp(int mouseX, int mouseY, int pointer, int button) {
		return false;
	}
	
	default boolean mouseCancelled(int mouseX, int mouseY, int pointer, int button) {
		return false;
	}
	
	default boolean mouseDragged(int mouseX, int mouseY, int pointer) {
		return false;
	}
	
	default boolean mouseMoved(int mouseX, int mouseY) {
		return false;
	}
	
	default boolean scrolled(float amountX, float amountY) {
		return false;
	}
	
	@Override
	default void dispose() {}
}
