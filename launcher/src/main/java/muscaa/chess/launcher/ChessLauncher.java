package muscaa.chess.launcher;

import javax.swing.JFrame;

public class ChessLauncher {
	
    public static void main(String[] args) throws Exception {
    	JFrame frame = new JFrame("Launcher");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(300, 300);
    	frame.setVisible(true);
    }
}
