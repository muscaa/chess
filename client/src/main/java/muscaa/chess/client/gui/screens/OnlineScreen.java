package muscaa.chess.client.gui.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.kotcrab.vis.ui.VisUI;

import muscaa.chess.client.Client;
import muscaa.chess.client.config.ServersConfig;
import muscaa.chess.client.gui.ChildGuiScreen;
import muscaa.chess.client.gui.GuiScreen;
import muscaa.chess.client.gui.widgets.WLabel;
import muscaa.chess.client.gui.widgets.WPanel;
import muscaa.chess.client.gui.widgets.WScrollPane;
import muscaa.chess.client.gui.widgets.WTable;
import muscaa.chess.client.gui.widgets.WTextButton;

public class OnlineScreen extends ChildGuiScreen {
	
	private ButtonGroup<Button> group;
	private WTextButton joinButton;
	private WTextButton editButton;
	private WTextButton deleteButton;
	
	public OnlineScreen(GuiScreen parent) {
		super(parent);
	}
	
	@Override
	protected void init() {
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
		for (ServersConfig.Server server : Client.INSTANCE.getServersConfig()) {
            Button button = serverEntry(server);
            group.add(button);
            content.add(button);
            content.row();
		}
		
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
		
		button.add(new WLabel(server.address + ":" + server.port, FONT_SMALL, Color.GRAY));
		button.row();
		
		return button;
	}
	
	private WTable footer() {
		WTable row1 = new WTable();
		row1.defaults().growX().pad(PAD_SMALL).minHeight(BUTTON_HEIGHT).colspan(2);
		
        joinButton = new WTextButton("Join");
        joinButton.addActionListener(w -> {
        	ServersConfig.Server server = Client.INSTANCE.getServersConfig().get(group.getCheckedIndex());
        	Client.INSTANCE.getNetworkClient().connect(server);
        });
        row1.add(joinButton);
        
		WTextButton add = new WTextButton("Add");
		add.addActionListener(w -> {
			Client.INSTANCE.getGuiLayer().setScreen(new ServerFormScreen(this, "Add", (name, address, port) -> {
				Client.INSTANCE.getServersConfig().modify(list -> {
					list.add(new ServersConfig.Server(name, address, port));
				});
			}));
		});
        row1.add(add);
        
		WTable row2 = new WTable();
		row2.defaults().growX().pad(PAD_SMALL).minHeight(BUTTON_HEIGHT).colspan(2);
		
		editButton = new WTextButton("Edit");
		editButton.addActionListener(w -> {
			ServersConfig.Server server = Client.INSTANCE.getServersConfig().get(group.getCheckedIndex());
			Client.INSTANCE.getGuiLayer().setScreen(new ServerFormScreen(this, "Edit", server.name, server.address, server.port, (name, address, port) -> {
				Client.INSTANCE.getServersConfig().modify(list -> {
					server.name = name;
					server.address = address;
					server.port = port;
				});
			}));
		});
        row2.add(editButton);
        
        deleteButton = new WTextButton("Delete");
        deleteButton.addActionListener(w -> {
        	Client.INSTANCE.getServersConfig().modify(list -> {
            	list.remove(group.getCheckedIndex());
            });
        	Client.INSTANCE.getGuiLayer().setScreen(this);
        });
        row2.add(deleteButton);
        
		WTextButton cancelButton = new WTextButton("Cancel");
		cancelButton.addActionListener(w -> Client.INSTANCE.getGuiLayer().setScreen(parent));
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
