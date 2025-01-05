package muscaa.chess.client.gui.widgets;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisCheckBox;

import fluff.functions.gen.obj.VoidFunc1;

public class WCheckBox extends VisCheckBox {

	public WCheckBox(String text) {
		super(text);
		
		init();
	}

	public WCheckBox(String text, boolean checked) {
		super(text, checked);
		
		init();
	}
	
	private void init() {
		clearChildren();
		add(getLabel());
		add(getImageStack());
		
		getLabelCell().size(getWidth() - getHeight(), getHeight());
	}
	
	public void addActionListener(VoidFunc1<WCheckBox> onAction) {
		addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if (onAction != null) onAction.invoke(WCheckBox.this);
			}
		});
	}
}
