package muscaa.chess.client.gui.widgets;

import muscaa.chess.client.config.Theme;
import muscaa.chess.client.gui.ColorDrawable;

public class WPanel extends WTable {
	
	public WPanel() {
		super();
		
		init();
	}
	
	public WPanel(boolean fillParent) {
		super(fillParent);
		
		init();
	}
	
	private void init() {
		setBackground(new ColorDrawable(Theme.PANEL));
	}
}
