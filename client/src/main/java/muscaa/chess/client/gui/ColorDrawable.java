package muscaa.chess.client.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import muscaa.chess.client.utils.Screen;
import muscaa.chess.client.utils.Shapes;

public class ColorDrawable implements Drawable {
	
	private final Color color;
	
	private float leftWidth;
	private float rightWidth;
	private float topHeight;
	private float bottomHeight;
	private float minWidth;
	private float minHeight;
	
	public ColorDrawable(Color color) {
		this.color = color;
	}
	
	@Override
	public void draw(Batch batch, float x, float y, float width, float height) {
		batch.end();
		Matrix4 old = Screen.SHAPES.getTransformMatrix().cpy();
		Screen.SHAPES.setTransformMatrix(batch.getTransformMatrix());
		Shapes.rect(x, y, width, height, color);
		Screen.SHAPES.setTransformMatrix(old);
		batch.begin();
	}
	
	@Override
	public float getLeftWidth() {
		return leftWidth;
	}
	
	@Override
	public void setLeftWidth(float leftWidth) {
		this.leftWidth = leftWidth;
	}
	
	@Override
	public float getRightWidth() {
		return rightWidth;
	}
	
	@Override
	public void setRightWidth(float rightWidth) {
		this.rightWidth = rightWidth;
	}
	
	@Override
	public float getTopHeight() {
		return topHeight;
	}
	
	@Override
	public void setTopHeight(float topHeight) {
		this.topHeight = topHeight;
	}
	
	@Override
	public float getBottomHeight() {
		return bottomHeight;
	}
	
	@Override
	public void setBottomHeight(float bottomHeight) {
		this.bottomHeight = bottomHeight;
	}
	
	@Override
	public float getMinWidth() {
		return minWidth;
	}
	
	@Override
	public void setMinWidth(float minWidth) {
		this.minWidth = minWidth;
	}
	
	@Override
	public float getMinHeight() {
		return minHeight;
	}
	
	@Override
	public void setMinHeight(float minHeight) {
		this.minHeight = minHeight;
	}
}
