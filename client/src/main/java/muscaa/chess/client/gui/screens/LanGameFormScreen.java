package muscaa.chess.client.gui.screens;

import java.io.IOException;

import com.kotcrab.vis.ui.util.IntDigitsOnlyFilter;

import fluff.network.NetworkException;
import muscaa.chess.board.Lobby;
import muscaa.chess.client.board.RemoteBoard;
import muscaa.chess.client.config.ServersConfig;
import muscaa.chess.client.gui.ChildGuiScreen;
import muscaa.chess.client.gui.GuiScreen;
import muscaa.chess.client.gui.widgets.WPanel;
import muscaa.chess.client.gui.widgets.WTable;
import muscaa.chess.client.gui.widgets.WTextButton;
import muscaa.chess.client.gui.widgets.WTextField;
import muscaa.chess.client.network.ChessClient;
import muscaa.chess.network.ChessServer;
import muscaa.chess.network.ServerContextRegistry;
import muscaa.chess.network.play.ServerPlayNetHandler;

public class LanGameFormScreen extends ChildGuiScreen {
	
	private WTextField portField;
	private WTextButton startServerButton;
	
	public LanGameFormScreen(GuiScreen parent) {
		super(parent);
	}
	
	@Override
	protected void init() {
		WTable main = new WTable(true);
		main.defaults().growX().pad(PAD_LARGE).maxWidth(PANEL_MEDIUM);
		main.add(form());
		
		update();
		
		stage.addActor(main);
	}
	
	private void update() {
		startServerButton.setDisabled(!portField.isInputValid());
	}
	
	private WPanel form() {
		WTable content = new WTable();
		content.defaults().growX().pad(PAD_SMALL).minHeight(BUTTON_HEIGHT);
		
		portField = new WTextField("40755");
		portField.setMessageText("Port");
		portField.addActionListener(w -> update());
		portField.setTextFieldFilter(new IntDigitsOnlyFilter(false));
		portField.addValidator(text -> {
			if (text.isBlank()) return false;
			
			try {
				int i = Integer.parseInt(text);
				return i > 0 && i <= 65535;
			} catch (NumberFormatException e) {}
			return false;
		});
		content.add(portField);
		content.row();
		
		content.add();
		content.row();
		
		startServerButton = new WTextButton("Start Server");
		startServerButton.addActionListener(w -> {
			int port = Integer.parseInt(portField.getText());
			
    		try {
    			/*if (Server.INSTANCE.getNetwork() != null && Server.INSTANCE.getNetwork().getServer().isRunning()) {
    				Server.INSTANCE.stop();
    			} else {
    				Server.INSTANCE.start();
    				*/
    				Lobby lobby = new Lobby();
    				ServerContextRegistry.PLAY.get().setHandlerFunc(() -> new ServerPlayNetHandler(lobby));
    				
    				ChessServer server = new ChessServer(port);
    				server.start(true);
    			
            		ChessClient client = new ChessClient();
                	client.connect(new ServersConfig.Server("LAN Game", "localhost", port));
            		
    				chess.setBoard(new RemoteBoard(client));
    			//}
			} catch (IOException | NetworkException e) {
				e.printStackTrace();
				
				chess.setScreen(new DisconnectedScreen(e.toString()));
			}
		});
		content.add(startServerButton);
		content.row();
		
		WTextButton cancelButton = new WTextButton("Cancel");
		cancelButton.addActionListener(w -> chess.setScreen(parent));
		content.add(cancelButton);
		content.row();
		
		WPanel form = new WPanel();
		form.defaults().growX().pad(PAD_MEDIUM);
		form.add(content);
		
		return form;
	}
}
