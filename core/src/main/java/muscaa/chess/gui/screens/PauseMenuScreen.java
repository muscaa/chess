package muscaa.chess.gui.screens;

import com.badlogic.gdx.Gdx;
import com.kotcrab.vis.ui.widget.VisTable;

import muscaa.chess.gui.GuiScreen;
import muscaa.chess.gui.Widgets;

public class PauseMenuScreen extends GuiScreen {
	
    private int width;
    private int height;
    
    private VisTable table;
	
	@Override
	protected void init() {
		table = new VisTable();
		table.defaults().pad(4.0F);
		table.setFillParent(true);
		//table.setSize(viewport.getWorldWidth(), viewport.getWorldHeight());
		
        Widgets.button(table::add, "Toggle Fullscreen", (button) -> {
        	if (!Gdx.graphics.supportsDisplayModeChange()) return;
        	
        	if (Gdx.graphics.isFullscreen()) {
        		Gdx.graphics.setWindowedMode(width, height);
        	} else {
        		width = Gdx.graphics.getWidth();
        		height = Gdx.graphics.getHeight();
        		
        		Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
        	}
        });
		Widgets.button(table::add, "Exit", (button) -> Gdx.app.exit());
		
		stage.addActor(table);
	}
	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		
		//table.setSize(viewport.getWorldWidth(), viewport.getWorldHeight());
	}
}
