package muscaa.chess.client.gui.widgets;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisSlider;

import fluff.functions.gen.obj.VoidFunc1;

public class WSlider extends VisSlider {
	
	public WSlider(float min, float max, float step, float value, boolean vertical) {
		super(min, max, step, vertical);
		
		setValue(value);
		
		init();
	}
	
	public WSlider(float min, float max, float step, float value) {
		this(min, max, step, value, false);
	}
	
	public WSlider(float min, float max, float step) {
		this(min, max, step, min);
	}
	
	public WSlider(float min, float max) {
		this(min, max, 1.0F);
	}
	
	private void init() {
		
	}
	
	public void addActionListener(VoidFunc1<WSlider> onAction) {
		addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if (onAction != null) onAction.invoke(WSlider.this);
			}
		});
	}
}
