package muscaa.chess.client.utils;

import com.badlogic.gdx.Gdx;

public class WindowUtils {
	
    private static int lastWidth;
    private static int lastHeight;
	
	public static void setFullscreen(boolean fullscreen) {
    	if (!Gdx.graphics.supportsDisplayModeChange()) return;
    	
    	if (Gdx.graphics.isFullscreen() && !fullscreen) {
    		Gdx.graphics.setWindowedMode(lastWidth, lastHeight);
    	} else if (!Gdx.graphics.isFullscreen() && fullscreen) {
    		lastWidth = Gdx.graphics.getWidth();
    		lastHeight = Gdx.graphics.getHeight();
    		
    		Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
    	}
	}
	
	public static void exit() {
		Gdx.app.exit();
	}
}
