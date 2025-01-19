package muscaa.chess.client.gui.screens;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.kotcrab.vis.ui.VisUI;

import fluff.network.NetworkException;
import muscaa.chess.client.board.RemoteBoard;
import muscaa.chess.client.config.ServersConfig;
import muscaa.chess.client.gui.ChildGuiScreen;
import muscaa.chess.client.gui.GuiScreen;
import muscaa.chess.client.gui.widgets.WLabel;
import muscaa.chess.client.gui.widgets.WPanel;
import muscaa.chess.client.gui.widgets.WScrollPane;
import muscaa.chess.client.gui.widgets.WTable;
import muscaa.chess.client.gui.widgets.WTextButton;
import muscaa.chess.client.network.ConnectChessClient;
import muscaa.chess.client.network.PingChessClient;
import muscaa.chess.client.network.ping.packets.CPacketPing;

public class OnlineScreen extends ChildGuiScreen {
	
	private ButtonGroup<Button> group;
	private Map<ServersConfig.Server, WLabel> servers;
	private WTextButton joinButton;
	private WTextButton editButton;
	private WTextButton deleteButton;
	
	public OnlineScreen(GuiScreen parent) {
		super(parent);
	}
	
	@Override
	protected void init() {
		chess.serversConfig.load();
		
		WTable main = new WTable(true);
		main.defaults().growX().pad(PAD_LARGE).maxWidth(PANEL_LARGE);
		
		WScrollPane serversScroll = new WScrollPane(servers());
		main.add(serversScroll).growY().padBottom(PAD_SMALL);
		main.row();
		
		WTable footer = footer();
		main.add(footer).padTop(PAD_SMALL);
		main.row();
		
		update();
		
		stage.addActor(main);
		stage.setScrollFocus(serversScroll);
	}
	
	private void update() {
		boolean disabled = group.getCheckedIndex() == -1;
		joinButton.setDisabled(disabled);
		editButton.setDisabled(disabled);
		deleteButton.setDisabled(disabled);
	}
	
	private WPanel servers() {
		WTable content = new WTable();
		content.defaults().growX().pad(PAD_SMALL);
		content.top();
		
		group = new ButtonGroup<>();
		servers = new HashMap<>();
		for (ServersConfig.Server server : chess.serversConfig) {
            Button button = serverEntry(server);
            group.add(button);
            content.add(button);
            content.row();
		}
		
		refresh();
		
		WPanel servers = new WPanel();
		servers.defaults().growX().pad(PAD_MEDIUM);
		servers.top();
		servers.add(content);
		
		return servers;
	}
	
	private Button serverEntry(ServersConfig.Server server) {
		Button button = new Button(VisUI.getSkin(), "toggle");
		button.defaults().growX();
		
		button.add(new WLabel(server.name));
		button.row();
		
		WLabel label = new WLabel("", FONT_SMALL, Color.GRAY);//new WLabel(server.address + ":" + server.port, FONT_SMALL, Color.GRAY);
		label.setWrap(true);
		servers.put(server, label);
		
		button.add(label);
		button.row();
		
		return button;
	}
	
	private void refresh() {
		for (Map.Entry<ServersConfig.Server, WLabel> entry : servers.entrySet()) {
			entry.getValue().setText("Pinging...");
			
			PingChessClient pingClient = new PingChessClient();
			CompletableFuture<CPacketPing> pingFuture = pingClient.ping(entry.getKey());
			try {
				CPacketPing packet = pingFuture.get();
				entry.getValue().setText(packet.getMotd() + " " + packet.getPlayers() + "/" + packet.getMaxPlayers());
			} catch (InterruptedException | ExecutionException e) {
				entry.getValue().setText(e.getMessage());
			}
		}
	}
	
	private WTable footer() {
		WTable row1 = new WTable();
		row1.defaults().growX().pad(PAD_SMALL).minHeight(BUTTON_HEIGHT).colspan(2);
		
        joinButton = new WTextButton("Join");
        joinButton.addActionListener(w -> {
        	try {
        		ServersConfig.Server server = chess.serversConfig.get(group.getCheckedIndex());
        		
        		ConnectChessClient client = new ConnectChessClient();
            	client.connect(server);
        		
				chess.setBoard(new RemoteBoard(client));
			} catch (IOException | NetworkException e) {
				e.printStackTrace();
				
				chess.setScreen(new DisconnectedScreen(e.toString()));
			}
        });
        row1.add(joinButton);
        
        WTextButton lanGameButton = new WTextButton("LAN Game");
        lanGameButton.addActionListener(w -> {
        	chess.setScreen(new LanGameFormScreen(this));
        });
        row1.add(lanGameButton);
        
		WTextButton add = new WTextButton("Add");
		add.addActionListener(w -> {
			chess.setScreen(new ServerFormScreen(this, "Add", (name, address, port) -> {
				chess.serversConfig.add(name, address, port);
			}));
		});
        row1.add(add);
        
		WTable row2 = new WTable();
		row2.defaults().growX().pad(PAD_SMALL).minHeight(BUTTON_HEIGHT).colspan(2);
		
		editButton = new WTextButton("Edit");
		editButton.addActionListener(w -> {
			ServersConfig.Server server = chess.serversConfig.get(group.getCheckedIndex());
			chess.setScreen(new ServerFormScreen(this, "Edit", server.name, server.address, server.port, (name, address, port) -> {
				server.name = name;
				server.address = address;
				server.port = port;
				chess.serversConfig.save();
			}));
		});
        row2.add(editButton);
        
        deleteButton = new WTextButton("Delete");
        deleteButton.addActionListener(w -> {
        	chess.serversConfig.remove(group.getCheckedIndex());
        	chess.setScreen(this);
        });
        row2.add(deleteButton);
        
        WTextButton refreshButton = new WTextButton("Refresh");
        refreshButton.addActionListener(w -> {
        	refresh();
        });
        row2.add(refreshButton);
        
		WTextButton cancelButton = new WTextButton("Cancel");
		cancelButton.addActionListener(w -> chess.setScreen(parent));
        row2.add(cancelButton);
        
        WTable footer = new WTable();
		footer.defaults().growX();
		
		footer.add(row1);
		footer.row();
		
		footer.add(row2);
		footer.row();
        
        return footer;
	}
}
