package muscaa.chess.client.gui.screens;

import java.util.Map;

import muscaa.chess.chat.ChatColor;
import muscaa.chess.client.Client;
import muscaa.chess.client.assets.FontRegistry;
import muscaa.chess.client.gui.ChildGuiScreen;
import muscaa.chess.client.gui.GuiScreen;
import muscaa.chess.client.gui.widgets.WLabel;
import muscaa.chess.client.gui.widgets.WPanel;
import muscaa.chess.client.gui.widgets.WScrollPane;
import muscaa.chess.client.gui.widgets.WTable;
import muscaa.chess.client.gui.widgets.WTextButton;
import muscaa.chess.modloader.ModInfo;

public class ModsScreen extends ChildGuiScreen {
	
	public ModsScreen(GuiScreen parent) {
		super(parent);
	}
	
	@Override
	protected void init() {
		WTable main = new WTable(true);
		main.defaults().growX().pad(PAD_LARGE).maxWidth(PANEL_LARGE);
		
		WScrollPane serversScroll = new WScrollPane(mods());
		main.add(serversScroll).growY().padBottom(PAD_SMALL);
		main.row();
		
		WTable footer = footer();
		main.add(footer).padTop(PAD_SMALL);
		main.row();
		
		stage.addActor(main);
		stage.setScrollFocus(serversScroll);
	}
	
	private WPanel mods() {
		WTable content = new WTable();
		content.defaults().growX().pad(PAD_SMALL);
		content.top();
		
		for (Map.Entry<String, ModInfo> entry : Client.MOD_LOADER.modInfos.entrySet()) {
            WPanel panel = modEntry(entry.getValue());
            
            content.add(panel);
            content.row();
		}
		
		WPanel mods = new WPanel();
		mods.defaults().growX().pad(PAD_MEDIUM);
		mods.top();
		mods.add(content);
		
		return mods;
	}
	
	private WPanel modEntry(ModInfo modInfo) {
		WPanel panel = new WPanel();
		panel.defaults().growX().minHeight(25).pad(0, PAD_SMALL, 0, PAD_SMALL);
		
		WTable title = new WTable();
		title.add(new WLabel(modInfo.getName())).growX();
		title.add(new WLabel(modInfo.getVersion(), FontRegistry.VARELA_18.get(), ChatColor.GRAY));
		
		panel.add(title);
		panel.row();
		
		WLabel id = new WLabel(modInfo.getID(), FontRegistry.VARELA_18.get(), ChatColor.GOLD);
		id.setWrap(true);
		panel.add(id);
		panel.row();
		
		WLabel description = new WLabel(modInfo.getDescription(), FontRegistry.VARELA_18.get(), ChatColor.GRAY);
		description.setWrap(true);
		panel.add(description);
		panel.row();
		
		return panel;
	}
	
	private WTable footer() {
		WTable row1 = new WTable();
		row1.defaults().growX().pad(PAD_SMALL).minHeight(BUTTON_HEIGHT).colspan(2);
		
		WTextButton backButton = new WTextButton("Back");
		backButton.addActionListener(w -> chess.setScreen(parent));
        row1.add(backButton);
        
        WTable footer = new WTable();
		footer.defaults().growX();
		
		footer.add(row1);
		footer.row();
		
        return footer;
	}
}
