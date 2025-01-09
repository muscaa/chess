package muscaa.chess.client.gui.screens;

import com.kotcrab.vis.ui.util.IntDigitsOnlyFilter;

import fluff.functions.gen._int.VoidFunc1Int;
import muscaa.chess.client.Client;
import muscaa.chess.client.gui.ChildGuiScreen;
import muscaa.chess.client.gui.GuiScreen;
import muscaa.chess.client.gui.widgets.WPanel;
import muscaa.chess.client.gui.widgets.WTable;
import muscaa.chess.client.gui.widgets.WTextButton;
import muscaa.chess.client.gui.widgets.WTextField;

public class LanGameFormScreen extends ChildGuiScreen {
	
	private final VoidFunc1Int onSubmit;
	
	private WTextField portField;
	private WTextButton startServerButton;
	
	public LanGameFormScreen(GuiScreen parent, VoidFunc1Int onSubmit) {
		super(parent);
		
		this.onSubmit = onSubmit;
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
			onSubmit.invoke(Integer.parseInt(portField.getText()));
		});
		content.add(startServerButton);
		content.row();
		
		WTextButton cancelButton = new WTextButton("Cancel");
		cancelButton.addActionListener(w -> Client.INSTANCE.getGuiLayer().setScreen(parent));
		content.add(cancelButton);
		content.row();
		
		WPanel form = new WPanel();
		form.defaults().growX().pad(PAD_MEDIUM);
		form.add(content);
		
		return form;
	}
}
