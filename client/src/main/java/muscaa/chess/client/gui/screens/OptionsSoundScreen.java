package muscaa.chess.client.gui.screens;

import com.badlogic.gdx.utils.Align;

import muscaa.chess.client.gui.ChildGuiScreen;
import muscaa.chess.client.gui.GuiScreen;
import muscaa.chess.client.gui.widgets.WLabel;
import muscaa.chess.client.gui.widgets.WSlider;
import muscaa.chess.client.gui.widgets.WTable;
import muscaa.chess.client.gui.widgets.WTextButton;
import muscaa.chess.config.values.IValueFloat;

public class OptionsSoundScreen extends ChildGuiScreen {
	
	public OptionsSoundScreen(GuiScreen parent) {
		super(parent);
	}
	
	@Override
	protected void init() {
		WTable content = new WTable();
		content.defaults().growX().pad(PAD_SMALL).minHeight(BUTTON_HEIGHT);
		
		content.add(volume("Master Volume", chess.settings.masterVolume));
		content.row();
		
		content.add(volume("Music Volume", chess.settings.musicVolume));
		content.row();
		
		content.add(volume("Sound Volume", chess.settings.soundVolume));
		content.row();
		
		content.add();
		content.row();
		
		WTextButton backButton = new WTextButton("Back");
		backButton.addActionListener(w -> chess.setScreen(parent));
		content.add(backButton);
		content.row();
		
		WTable main = new WTable(true);
		main.defaults().growX().pad(PAD_LARGE).maxWidth(PANEL_MEDIUM);
		main.add(content);
		
		stage.addActor(main);
	}
	
	private WTable volume(String name, IValueFloat value) {
		WTable content = new WTable();
		content.defaults().growX().pad(PAD_SMALL);
		
		WLabel masterVolumeLabel = new WLabel(name, Align.center);
		content.add(masterVolumeLabel);
		content.row();
		
		WSlider masterVolumeSlider = new WSlider(0.0F, 1.0F, 0.05F, value.get());
		masterVolumeSlider.addActionListener(w -> value.set(w.getValue()));
		content.add(masterVolumeSlider);
		content.row();
		
		return content;
	}
}
