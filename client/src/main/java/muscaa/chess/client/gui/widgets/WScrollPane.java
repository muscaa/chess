package muscaa.chess.client.gui.widgets;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.kotcrab.vis.ui.widget.VisScrollPane;

public class WScrollPane extends VisScrollPane {
	
	public WScrollPane(Actor widget) {
		super(widget);
		
		init();
	}
	
	public WScrollPane(Actor widget, boolean fillParent) {
		super(widget);
		
		setFillParent(fillParent);
		
		init();
	}
	
	private void init() {
		setFadeScrollBars(false);
		setFlickScroll(false);
		setScrollbarsOnTop(true);
	}
}
