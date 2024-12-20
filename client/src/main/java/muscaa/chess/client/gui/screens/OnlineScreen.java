package muscaa.chess.client.gui.screens;

import com.kotcrab.vis.ui.widget.VisTable;

import muscaa.chess.client.Core;
import muscaa.chess.client.gui.GuiScreen;
import muscaa.chess.client.gui.Widgets;

public class OnlineScreen extends GuiScreen {
	
	private final GuiScreen parent;
	
	private String ip;
	private int port;
	private String username;
	
	public OnlineScreen(GuiScreen parent) {
		this.parent = parent;
	}
	
	@Override
	protected void init() {
		ip = "localhost";
		port = 40755;
		username = "muscaa";
		
		VisTable main = Widgets.table(true);
		
		Widgets.textfield(main, "Server IP", ip, false, (textfield) -> ip = textfield.getText());
		main.row();
		Widgets.textfield(main, "Server Port", String.valueOf(port), true, (textfield) -> port = Integer.valueOf(textfield.getText()));
		main.row();
		Widgets.textfield(main, "Username", username, false, (textfield) -> username = textfield.getText());
		main.row();
		Widgets.button(main, "Connect", (button) -> Core.INSTANCE.getNetwork().connect(ip, port, username));
		main.row();
		Widgets.empty(main);
		main.row();
		Widgets.button(main, "Back", (button) -> setScreen(parent));
		main.row();
		
		stage.addActor(main);
	}
}
