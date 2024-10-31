package muscaa.chess.layer;

import java.util.Iterator;
import java.util.LinkedList;

public class LayerManager implements ILayer {
	
	private final LinkedList<ILayer> reg = new LinkedList<>();
	private ILayer hovered;
	
	public void registerFirst(ILayer input) {
		synchronized (reg) {
			reg.addFirst(input);
		}
	}
	
	public void register(ILayer input) {
		synchronized (reg) {
			reg.add(input);
		}
	}
	
	public void unregister(ILayer input) {
		synchronized (reg) {
			if (reg.remove(input)) {
				input.dispose();
			}
		}
	}
	
	@Override
	public void render(int mouseX, int mouseY, float delta) {
		hover(mouseX, mouseY);
		
		synchronized (reg) {
			Iterator<ILayer> it = reg.iterator();
			while (it.hasNext()) {
				ILayer l = it.next();
				
				if (l.equals(hovered)) {
					l.render(mouseX, mouseY, delta);
				} else {
					l.render(-1, -1, delta);
				}
			}
		}
	}
	
	@Override
	public void resize(int width, int height) {
		synchronized (reg) {
			Iterator<ILayer> it = reg.iterator();
			while (it.hasNext()) {
				ILayer l = it.next();
				l.resize(width, height);
			}
		}
	}
	
	@Override
	public boolean hover(int mouseX, int mouseY) {
		synchronized (reg) {
			Iterator<ILayer> it = reg.descendingIterator();
			while (it.hasNext()) {
				ILayer l = it.next();
				if (l.hover(mouseX, mouseY)) {
					hovered = l;
					return true;
				}
			}
			hovered = null;
			return false;
		}
	}
	
	@Override
	public boolean keyDown(int keycode) {
		synchronized (reg) {
			Iterator<ILayer> it = reg.descendingIterator();
			while (it.hasNext()) {
				ILayer l = it.next();
				if (l.keyDown(keycode)) return true;
			}
			return false;
		}
	}
	
	@Override
	public boolean keyUp(int keycode) {
		synchronized (reg) {
			Iterator<ILayer> it = reg.descendingIterator();
			while (it.hasNext()) {
				ILayer l = it.next();
				if (l.keyUp(keycode)) return true;
			}
			return false;
		}
	}
	
	@Override
	public boolean keyTyped(char character) {
		synchronized (reg) {
			Iterator<ILayer> it = reg.descendingIterator();
			while (it.hasNext()) {
				ILayer l = it.next();
				if (l.keyTyped(character)) return true;
			}
			return false;
		}
	}
	
	@Override
	public boolean mouseDown(int mouseX, int mouseY, int pointer, int button) {
		synchronized (reg) {
			Iterator<ILayer> it = reg.descendingIterator();
			while (it.hasNext()) {
				ILayer l = it.next();
				if (l.mouseDown(mouseX, mouseY, pointer, button)) return true;
			}
			return false;
		}
	}
	
	@Override
	public boolean mouseUp(int mouseX, int mouseY, int pointer, int button) {
		synchronized (reg) {
			Iterator<ILayer> it = reg.descendingIterator();
			while (it.hasNext()) {
				ILayer l = it.next();
				if (l.mouseUp(mouseX, mouseY, pointer, button)) return true;
			}
			return false;
		}
	}
	
	@Override
	public boolean mouseCancelled(int mouseX, int mouseY, int pointer, int button) {
		synchronized (reg) {
			Iterator<ILayer> it = reg.descendingIterator();
			while (it.hasNext()) {
				ILayer l = it.next();
				if (l.mouseCancelled(mouseX, mouseY, pointer, button)) return true;
			}
			return false;
		}
	}
	
	@Override
	public boolean mouseDragged(int mouseX, int mouseY, int pointer) {
		synchronized (reg) {
			Iterator<ILayer> it = reg.descendingIterator();
			while (it.hasNext()) {
				ILayer l = it.next();
				if (l.mouseDragged(mouseX, mouseY, pointer)) return true;
			}
			return false;
		}
	}
	
	@Override
	public boolean mouseMoved(int mouseX, int mouseY) {
		synchronized (reg) {
			Iterator<ILayer> it = reg.descendingIterator();
			while (it.hasNext()) {
				ILayer l = it.next();
				if (l.mouseMoved(mouseX, mouseY)) return true;
			}
			return false;
		}
	}
	
	@Override
	public boolean scrolled(float amountX, float amountY) {
		synchronized (reg) {
			Iterator<ILayer> it = reg.descendingIterator();
			while (it.hasNext()) {
				ILayer l = it.next();
				if (l.scrolled(amountX, amountY)) return true;
			}
			return false;
		}
	}
	
	@Override
	public void dispose() {
		synchronized (reg) {
			Iterator<ILayer> it = reg.iterator();
			while (it.hasNext()) {
				ILayer l = it.next();
				l.dispose();
			}
			reg.clear();
		}
	}
}
