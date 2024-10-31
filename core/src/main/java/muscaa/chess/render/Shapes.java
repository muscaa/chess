package muscaa.chess.render;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Shapes {
	
	public static void rect(float x, float y, float width, float height, Color color) {
		Screen.beginShapes(ShapeType.Filled);
		
		Screen.SHAPES.setColor(color);
		Screen.SHAPES.rect(x, y, width, height);
		
		Screen.endShapes();
	}
}
