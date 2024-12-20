package muscaa.chess.render;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Screen {
	
	public static final OrthographicCamera CAMERA = new OrthographicCamera();
	public static final Viewport VIEWPORT = new ScreenViewport(CAMERA);
	public static final SpriteBatch SPRITES = new SpriteBatch();
	public static final ShapeRenderer SHAPES = new ShapeRenderer();
	
	public static int WIDTH = 0;
	public static int HEIGHT = 0;
	
	public static void init(int width, int height) {
		VIEWPORT.update(width, height, true);
		
		WIDTH = (int) VIEWPORT.getWorldWidth();
		HEIGHT = (int) VIEWPORT.getWorldHeight();
	}
	
	public static void beginScreen() {
		VIEWPORT.apply(true);
		SPRITES.setProjectionMatrix(CAMERA.combined);
		SHAPES.setProjectionMatrix(CAMERA.combined);
	}
	
	public static void endScreen() {}
	
	public static void beginSprites() {
		SPRITES.enableBlending();
		SPRITES.begin();
	}
	
	public static void endSprites() {
		SPRITES.end();
		SPRITES.disableBlending();
	}
	
	public static void beginShapes(ShapeType type) {
		RenderUtils.enableBlend();
		SHAPES.begin(type);
	}
	
	public static void endShapes() {
		SHAPES.end();
		RenderUtils.disableBlend();
	}
	
	public static void dispose() {
		SHAPES.dispose();
		SPRITES.dispose();
	}
}
