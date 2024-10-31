package muscaa.chess.render;

import com.badlogic.gdx.Gdx;

public class WindowUtils {
	
    private static int width;
    private static int height;
	
	public static void toggleFullscreen() {
    	if (!Gdx.graphics.supportsDisplayModeChange()) return;
    	
    	if (Gdx.graphics.isFullscreen()) {
    		Gdx.graphics.setWindowedMode(width, height);
    	} else {
    		width = Gdx.graphics.getWidth();
    		height = Gdx.graphics.getHeight();
    		
    		Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
    	}
	}
	
	public static void exit() {
		Gdx.app.exit();
	}
}
