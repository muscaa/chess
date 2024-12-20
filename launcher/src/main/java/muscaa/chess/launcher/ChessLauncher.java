package muscaa.chess.launcher;

import muscaa.chess.launcher.gui.MainFrame;
import muscaa.chess.launcher.gui.panels.TabsPanel;

public class ChessLauncher {
	
	public static final ChessLauncher INSTANCE = new ChessLauncher();
	
	public final MainFrame mainFrame = new MainFrame();
	
	public void start() {
		mainFrame.setContentPane(new TabsPanel());
		mainFrame.setVisible(true);
	}
	
	public void stop() {
		mainFrame.dispose();
	}
}
