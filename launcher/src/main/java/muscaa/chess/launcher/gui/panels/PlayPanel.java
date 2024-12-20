package muscaa.chess.launcher.gui.panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PlayPanel extends JPanel {
	
	private static final long serialVersionUID = 8959004286375243427L;
	
	public PlayPanel() {
		setLayout(new BorderLayout());
		
		add(new LaunchPanel(), BorderLayout.SOUTH);
		
		MarkdownPanel md = new MarkdownPanel("""
				# Hello World
				""");
		JScrollPane scroll = new JScrollPane(md);
		scroll.setBorder(null);
		add(scroll, BorderLayout.CENTER);
	}
}
