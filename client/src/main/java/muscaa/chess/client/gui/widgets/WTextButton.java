package muscaa.chess.client.gui.widgets;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisTextButton;

import fluff.functions.gen.obj.VoidFunc1;

public class WTextButton extends VisTextButton {
	
	public WTextButton(String text) {
		super(text);
		
		init();
	}
	
	private void init() {
		
	}
	
	public void addActionListener(VoidFunc1<WTextButton> onAction) {
		addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if (onAction != null) onAction.invoke(WTextButton.this);
			}
		});
	}
}
