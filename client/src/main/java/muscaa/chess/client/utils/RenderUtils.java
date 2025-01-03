package muscaa.chess.client.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;

public class RenderUtils {
	
	public static Color color(int r, int g, int b) {
		return color(r, g, b, 255);
	}
	
	public static Color color(int r, int g, int b, int a) {
		return new Color(r / 255.0F, g / 255.0F, b / 255.0F, a / 255.0F);
	}
	
	public static void enableBlend() {
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	}
	
	public static void disableBlend() {
		Gdx.gl.glDisable(GL20.GL_BLEND);
	}
}
