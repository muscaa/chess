package muscaa.chess.client.main;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import muscaa.chess.client.ChessApplicationListener;

public class Main {
	
	public static void main(String[] args) {
		if (StartupHelper.startNewJvmIfRequired()) return;
		
		createApplication();
	}
	
	private static Lwjgl3Application createApplication() {
		return new Lwjgl3Application(new ChessApplicationListener(), getDefaultConfiguration());
	}
	
	private static Lwjgl3ApplicationConfiguration getDefaultConfiguration() {
		int samples = 16;
		
		Lwjgl3ApplicationConfiguration c = new Lwjgl3ApplicationConfiguration();
		c.setTitle("Chess");
		c.useVsync(true);
		c.setForegroundFPS(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate + 1);
		//c.setWindowedMode(16 * 100, 9 * 100);
		c.setWindowedMode(640, 480);
		c.setWindowIcon("libgdx128.png", "libgdx64.png", "libgdx32.png", "libgdx16.png");
		c.setBackBufferConfig(8, 8, 8, 8, 16, 0, samples);
		c.setWindowSizeLimits(640, 480, -1, -1);
		return c;
	}
}
