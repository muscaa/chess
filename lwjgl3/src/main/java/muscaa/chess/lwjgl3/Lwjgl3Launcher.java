package muscaa.chess.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import muscaa.chess.ChessGame;

public class Lwjgl3Launcher {
	
	public static void main(String[] args) {
		if (StartupHelper.startNewJvmIfRequired()) return;
		
		createApplication();
	}
	
	private static Lwjgl3Application createApplication() {
		return new Lwjgl3Application(ChessGame.INSTANCE, getDefaultConfiguration());
	}
	
	private static Lwjgl3ApplicationConfiguration getDefaultConfiguration() {
		int samples = 16;
		
		Lwjgl3ApplicationConfiguration c = new Lwjgl3ApplicationConfiguration();
		c.setTitle("Chess");
		c.useVsync(true);
		c.setForegroundFPS(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate + 1);
		c.setWindowedMode(16 * 100, 9 * 100);
		c.setWindowIcon("libgdx128.png", "libgdx64.png", "libgdx32.png", "libgdx16.png");
		c.setBackBufferConfig(8, 8, 8, 8, 16, 0, samples);
		return c;
	}
}