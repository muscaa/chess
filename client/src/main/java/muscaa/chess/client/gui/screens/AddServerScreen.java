package muscaa.chess.client.gui.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.util.IntDigitsOnlyFilter;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisValidatableTextField;

import muscaa.chess.client.Client;
import muscaa.chess.client.config.ServersConfig;
import muscaa.chess.client.gui.ChildGuiScreen;
import muscaa.chess.client.gui.GuiScreen;
import muscaa.chess.client.gui.Widgets;

public class AddServerScreen extends ChildGuiScreen {
	
	private VisValidatableTextField nameField;
	private VisValidatableTextField addressField;
	private VisValidatableTextField portField;
	private VisTextButton addButton;
	
	public AddServerScreen(GuiScreen parent) {
		super(parent);
	}
	
	@Override
	protected void init() {
		VisTable main = Widgets.table(new VisTable(), true);
		main.defaults().growX().pad(PAD_LARGE).maxWidth(PANEL_MEDIUM);
		main.add(form());
		
		update();
		
		stage.addActor(main);
	}
	
	private void update() {
		addButton.setDisabled(!nameField.isInputValid() || !addressField.isInputValid() || !portField.isInputValid());
	}
	
	private VisTable form() {
		VisTable main = Widgets.panel(new VisTable(), false);
		main.defaults().growX().pad(PAD_SMALL).height(BUTTON_HEIGHT);
		
		ChangeListener changeListener = new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				update();
			}
		};

		nameField = new VisValidatableTextField("Chess Server");
		nameField.setMessageText("Name");
		nameField.addListener(changeListener);
		nameField.addValidator(text -> !text.isBlank());
		main.add(nameField);
		main.row();
		
		addressField = new VisValidatableTextField();
		addressField.setMessageText("Address");
		addressField.addListener(changeListener);
		addressField.addValidator(text -> !text.isBlank());
		main.add(addressField);
		main.row();
		
		portField = new VisValidatableTextField();
		portField.setMessageText("Port");
		portField.addListener(changeListener);
		portField.setTextFieldFilter(new IntDigitsOnlyFilter(false));
		portField.addValidator(text -> {
			if (text.isBlank()) return false;
			
			int i = Integer.parseInt(text);
			return i > 0 && i <= 65535;
		});
		main.add(portField);
		main.row();
		
		addButton = Widgets.button(new VisTextButton("Add"), b -> {
			Client.INSTANCE.getServersConfig().modify(list -> {
				list.add(new ServersConfig.Server(nameField.getText(), addressField.getText(), Integer.parseInt(portField.getText())));
			});
			Client.INSTANCE.getGuiLayer().setScreen(parent);
		});
		main.add(addButton);
		main.row();
		
		VisTextButton cancelButton = Widgets.button(new VisTextButton("Cancel"), b -> Client.INSTANCE.getGuiLayer().setScreen(parent));
		main.add(cancelButton);
		main.row();
		
		return main;
	}
}
