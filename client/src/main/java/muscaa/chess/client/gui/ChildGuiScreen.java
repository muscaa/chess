package muscaa.chess.client.gui;

import com.badlogic.gdx.Input.Keys;

public abstract class ChildGuiScreen extends GuiScreen {
	
	protected final GuiScreen parent;
	
	public ChildGuiScreen(GuiScreen parent) {
		this.parent = parent;
	}
	
	@Override
	public boolean keyUp(int keycode) {
		boolean handled = super.keyUp(keycode);
		if (handled) return true;
		
		if (keycode == Keys.ESCAPE) {
			chess.setScreen(parent);
			return true;
		}
		
		return false;
	}
}
