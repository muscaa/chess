package muscaa.chess.launcher.gui.panels;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ModsPanel extends JPanel {
	
	private static final long serialVersionUID = 8959004286375243427L;
	
	public ModsPanel() {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		add(new JButton("mods 1"));
		add(new JButton("mods 2"));
	}
}
