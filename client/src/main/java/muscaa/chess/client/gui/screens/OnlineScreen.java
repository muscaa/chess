package muscaa.chess.client.gui.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisScrollPane;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

import muscaa.chess.client.Client;
import muscaa.chess.client.config.ServersConfig;
import muscaa.chess.client.gui.ChildGuiScreen;
import muscaa.chess.client.gui.GuiScreen;
import muscaa.chess.client.gui.Widgets;

public class OnlineScreen extends ChildGuiScreen {
	
	private ButtonGroup<Button> group;
	private VisTextButton delete;
	private VisTextButton join;
	
	public OnlineScreen(GuiScreen parent) {
		super(parent);
	}
	
	@Override
	protected void init() {
		VisTable main = Widgets.table(new VisTable(), true);
		main.defaults().growX().pad(PAD_LARGE).maxWidth(PANEL_LARGE);
		
		VisScrollPane servers = Widgets.scrollPane(new VisScrollPane(servers()), false);
		main.add(servers).growY().padBottom(PAD_MEDIUM);
		main.row();
		
		VisTable footer = footer();
		main.add(footer).padTop(PAD_MEDIUM);
		main.row();
		
		update();
		
		stage.addActor(main);
		stage.setScrollFocus(servers);
	}
	
	private void update() {
		boolean disabled = group.getCheckedIndex() == -1;
		delete.setDisabled(disabled);
		join.setDisabled(disabled);
	}
	
	private VisTable servers() {
		VisTable main = Widgets.panel(new VisTable(), false);
		main.defaults().growX().pad(PAD_SMALL);
		main.top();
		
		group = new ButtonGroup<>();
		for (ServersConfig.Server server : Client.INSTANCE.getServersConfig().getList()) {
            Button button = serverEntry(server);
            group.add(button);
            main.add(button);
            main.row();
		}
		return main;
	}
	
	private Button serverEntry(ServersConfig.Server server) {
		Button main = new Button(VisUI.getSkin(), "toggle");
		main.defaults().growX();
		
		main.add(Widgets.label(new VisLabel(server.name, FONT_DEFAULT, Color.WHITE)));
		main.row();
		
		main.add(Widgets.label(new VisLabel(server.address + ":" + server.port, FONT_SMALL, Color.GRAY)));
		main.row();
		
		return main;
	}
	
	private VisTable footer() {
		VisTable main = Widgets.table(new VisTable(), false);
		main.defaults().growX().pad(PAD_SMALL).height(BUTTON_HEIGHT);
		
		VisTextButton add = Widgets.button(new VisTextButton("Add"), b -> Client.INSTANCE.getGuiLayer().setScreen(new AddServerScreen(this)));
        main.add(add);
        
        delete = Widgets.button(new VisTextButton("Delete"), b -> {
        	Client.INSTANCE.getServersConfig().modify(list -> {
            	list.remove(group.getCheckedIndex());
            });
        	Client.INSTANCE.getGuiLayer().setScreen(this); // reload
        });
        main.add(delete);
        
        join = Widgets.button(new VisTextButton("Join"), b -> {
        	ServersConfig.Server server = Client.INSTANCE.getServersConfig().getList().get(group.getCheckedIndex());
        	Client.INSTANCE.getNetworkClient().connect(server);
        });
        main.add(join);
        
        return main;
	}
}
