package muscaa.chess.launcher.gui.panels;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;

import com.formdev.flatlaf.FlatClientProperties;

import muscaa.chess.launcher.ChessLauncher;

public class LaunchPanel extends JPanel {
	
	private static final long serialVersionUID = 8959004286375243427L;
	
	private JComboBox<String> version;
	private JButton launch;
	
	private CardLayout progressLayout;
	private JProgressBar progress;
	private JLabel progressLabel;
	
	public LaunchPanel() {
		setLayout(new BorderLayout(10, 10));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		add(new JSeparator(), BorderLayout.NORTH);
		addVersionAndLaunch();
		addProgress();
	}
	
	private void addVersionAndLaunch() {
		version = new JComboBox<>(new String[] { "latest", "1.0.0", "1.0.1", "1.0.2" });
		version.addActionListener(e -> {
			progressLabel.setText("Downloading " + version.getSelectedItem() + "...");
		});
		
		launch = new JButton("Launch");
		launch.putClientProperty(FlatClientProperties.STYLE_CLASS, "h3");
		launch.addActionListener(e -> {
			show("progress");
		});
		ChessLauncher.INSTANCE.mainFrame.getRootPane().setDefaultButton(launch);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(10, 10));
		panel.add(version, BorderLayout.CENTER);
		panel.add(launch, BorderLayout.EAST);
		
		add(panel, BorderLayout.CENTER);
	}
	
	private void addProgress() {
		JPanel panel = new JPanel();
		panel.setLayout(progressLayout = new CardLayout());
		
		panel.add("empty", new JPanel());
		
		JPanel progressPanel = new JPanel();
		progressPanel.setLayout(new BorderLayout(10, 10));
		
		progress = new JProgressBar();
		progress.setIndeterminate(true);
		
		progressLabel = new JLabel("Downloading...");
		
		progressPanel.add(progress, BorderLayout.SOUTH);
		progressPanel.add(progressLabel, BorderLayout.CENTER);
		
		panel.add("progress", progressPanel);
		
		add(panel, BorderLayout.SOUTH);
	}
	
	private void show(String name) {
		progressLayout.show(progress.getParent().getParent(), name);
	}
}
