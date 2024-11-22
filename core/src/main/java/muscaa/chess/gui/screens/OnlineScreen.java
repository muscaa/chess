package muscaa.chess.gui.screens;

import com.kotcrab.vis.ui.widget.VisTable;

import muscaa.chess.ChessGame;
import muscaa.chess.gui.GuiScreen;
import muscaa.chess.gui.Widgets;

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
		
		Widgets.textfield(main, "Server IP", ip, false, (textfield) -> ip = textfield.getText())
				.row();
		Widgets.textfield(main, "Server Port", String.valueOf(port), true, (textfield) -> port = Integer.valueOf(textfield.getText()))
				.row();
		Widgets.textfield(main, "Username", username, false, (textfield) -> username = textfield.getText())
				.row();
		Widgets.button(main, "Connect", (button) -> ChessGame.INSTANCE.getNetwork().connect(ip, port, username))
				.row();
		Widgets.empty(main)
				.row();
		Widgets.button(main, "Back", (button) -> setScreen(parent))
				.row();
		
		stage.addActor(main);
	}
}
