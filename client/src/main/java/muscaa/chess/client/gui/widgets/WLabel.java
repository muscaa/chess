package muscaa.chess.client.gui.widgets;

import com.badlogic.gdx.graphics.Color;
import com.kotcrab.vis.ui.widget.VisLabel;

public class WLabel extends VisLabel {
	
	public WLabel() {
		super();
		
		init();
	}
	
	public WLabel(CharSequence text, Color textColor) {
		super(text, textColor);
		
		init();
	}
	
	public WLabel(CharSequence text, int alignment) {
		super(text, alignment);
		
		init();
	}
	
	public WLabel(CharSequence text) {
		super(text);
		
		init();
	}
	
	public WLabel(CharSequence text, String fontName, Color color) {
		super(text, fontName, color);
		
		init();
	}
	
	private void init() {
		
	}
}
