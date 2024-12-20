package muscaa.chess.launcher.gui.panels;

import java.awt.BorderLayout;

import javax.swing.JEditorPane;
import javax.swing.JPanel;

import com.github.rjeschke.txtmark.Processor;

public class MarkdownPanel extends JPanel {
	
	private static final long serialVersionUID = 5025547644427219933L;
	
	public MarkdownPanel(String md) {
		String html = Processor.process(md);
		JEditorPane editor = new JEditorPane();
		editor.setContentType("text/html");
		editor.setText("""
				<html>
		  			<head>
		  				<style>
		  					body {
		  						font-family: Segoe UI;
		  						font-size: 14;
		  					}
		  				</style>
		  			</head>
		  			
		  			<body>
		  		""" + html + """
		  			</body>
		  		</html>
				""");
		editor.setEditable(false);
		
		setLayout(new BorderLayout());
		
		add(editor, BorderLayout.CENTER);
	}
}
