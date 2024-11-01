package muscaa.chess.gui.screens;

import java.util.Scanner;

import com.badlogic.gdx.graphics.Color;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

import fluff.network.packet.PacketContext;
import fluff.network.packet.channels.DefaultPacketChannel;
import muscaa.chess.ChessGame;
import muscaa.chess.assets.Sounds;
import muscaa.chess.board.Board;
import muscaa.chess.gui.GuiScreen;
import muscaa.chess.gui.Widgets;
import muscaa.chess.network.ChessClient;
import muscaa.chess.network.ClientNetHandler;
import muscaa.chess.network.IClientNetHandler;
import muscaa.chess.render.WindowUtils;
import muscaa.chess.shared.PacketMessage;

public class MainMenuScreen extends GuiScreen {
	
	@Override
	protected void init() {
		Sounds.AMBIENT.loop(0.5F);
		
		VisTable main = Widgets.table(true);
		
		Widgets.label(main, Widgets.FONT_TITLE, "Chess", Color.WHITE)
				.row();
		Widgets.button(main, "Offline", (button) -> {
			ChessGame.INSTANCE.getBoardLayer().setBoard(new Board());
			ChessGame.INSTANCE.getGuiLayer().setScreen(null);
			Sounds.AMBIENT.stop();
		})
				.row();
		Widgets.button(main, "Online", this::online)
				.row();
		Widgets.button(main, "Options", (button) -> ChessGame.INSTANCE.getGuiLayer().setScreen(new OptionsMenuScreen(this)))
				.row();
		Widgets.empty(main)
				.row();
		Widgets.button(main, "Exit", (button) -> WindowUtils.exit())
				.row();
		Widgets.empty(main, 100)
				.row();
		
		stage.addActor(main);
	}
	
	public static final PacketContext<IClientNetHandler> CLIENT_CONTEXT = new PacketContext<IClientNetHandler>("client_context")
			.extend(PacketMessage.COMMON_CONTEXT)
			;
	
	private void online(VisTextButton button) {
		try {
			ChessClient client = new ChessClient();
			client.setContext(CLIENT_CONTEXT, new ClientNetHandler());
			client.setChannel(new DefaultPacketChannel());
			client.connect("192.168.0.100", 40755);
			
			Scanner s = new Scanner(System.in);
			while (s.hasNextLine()) {
				String line = s.nextLine();
				if (line.equals("stop")) break;
				
				client.send(new PacketMessage(line));
			}
			s.close();
			
			client.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
