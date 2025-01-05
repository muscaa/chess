package muscaa.chess.client.gui.widgets;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisValidatableTextField;

import fluff.functions.gen.obj.VoidFunc1;

public class WTextField extends VisValidatableTextField {
	
	public WTextField() {
		super();
		
		init();
	}
	
	public WTextField(String text) {
		super(text);
		
		init();
	}
	
	private void init() {
		
	}
	
	public void addActionListener(VoidFunc1<WTextField> onAction) {
		addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if (onAction != null) onAction.invoke(WTextField.this);
			}
		});
	}
}
