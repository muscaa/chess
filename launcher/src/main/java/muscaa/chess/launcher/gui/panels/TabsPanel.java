package muscaa.chess.launcher.gui.panels;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;

import com.formdev.flatlaf.FlatClientProperties;

public class TabsPanel extends JPanel {
	
	private static final long serialVersionUID = 507064802342134657L;
	
	private JTabbedPane tabs;
	
	public TabsPanel() {
		setLayout(new BorderLayout());
		
		tabs = new JTabbedPane(JTabbedPane.LEFT, JTabbedPane.SCROLL_TAB_LAYOUT);
		addTab("Play", new PlayPanel());
		addTab("Mods", new ModsPanel());
		addTab("Settings", new SettingsPanel());
		
		add(tabs, BorderLayout.CENTER);
		
		JPanel footer = new JPanel();
		footer.setLayout(new BorderLayout());
		footer.add(new JSeparator(), BorderLayout.NORTH);
		
		JLabel version = new JLabel("v1.0.0");
		version.putClientProperty(FlatClientProperties.STYLE_CLASS, "mini");
		version.setBorder(BorderFactory.createEmptyBorder(0, 3, 3, 0));
		footer.add(version, BorderLayout.CENTER);
		add(footer, BorderLayout.SOUTH);
	}
	
	public JPanel addTab(String title, Component component) {
		tabs.addTab(title, component);
		
		int index = tabs.indexOfTab(title);
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(new BorderLayout(10, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		JLabel titleLabel = new JLabel(title);
		titleLabel.putClientProperty(FlatClientProperties.STYLE_CLASS, "h3");
		panel.add(titleLabel, BorderLayout.CENTER);
		
		tabs.setTabComponentAt(index, panel);
		
		return panel;
	}
}
