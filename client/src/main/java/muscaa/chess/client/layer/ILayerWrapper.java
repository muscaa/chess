package muscaa.chess.client.layer;

public interface ILayerWrapper extends ILayer {
	
	ILayer getWrappedLayer();
	
	@Override
	default void render(int mouseX, int mouseY, float delta) {
		getWrappedLayer().render(mouseX, mouseY, delta);
	}
	
	@Override
	default void resize(int width, int height) {
		getWrappedLayer().resize(width, height);
	}
	
	@Override
	default boolean hover(int mouseX, int mouseY) {
		return getWrappedLayer().hover(mouseX, mouseY);
	}
	
	@Override
	default boolean keyDown(int keycode) {
		return getWrappedLayer().keyDown(keycode);
	}
	
	@Override
	default boolean keyUp(int keycode) {
		return getWrappedLayer().keyUp(keycode);
	}
	
	@Override
	default boolean keyTyped(char character) {
		return getWrappedLayer().keyTyped(character);
	}
	
	@Override
	default boolean mouseDown(int mouseX, int mouseY, int pointer, int button) {
		return getWrappedLayer().mouseDown(mouseX, mouseY, pointer, button);
	}
	
	@Override
	default boolean mouseUp(int mouseX, int mouseY, int pointer, int button) {
		return getWrappedLayer().mouseUp(mouseX, mouseY, pointer, button);
	}
	
	@Override
	default boolean mouseCancelled(int mouseX, int mouseY, int pointer, int button) {
		return getWrappedLayer().mouseCancelled(mouseX, mouseY, pointer, button);
	}
	
	@Override
	default boolean mouseDragged(int mouseX, int mouseY, int pointer) {
		return getWrappedLayer().mouseDragged(mouseX, mouseY, pointer);
	}
	
	@Override
	default boolean mouseMoved(int mouseX, int mouseY) {
		return getWrappedLayer().mouseMoved(mouseX, mouseY);
	}
	
	@Override
	default boolean scrolled(float amountX, float amountY) {
		return getWrappedLayer().scrolled(amountX, amountY);
	}
	
	@Override
	default void dispose() {
		getWrappedLayer().dispose();
	}
}
