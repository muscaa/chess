package muscaa.chess.client.gui.screens;

import com.kotcrab.vis.ui.util.IntDigitsOnlyFilter;

import fluff.functions.gen.obj.obj._int.VoidFunc3ObjObjInt;
import muscaa.chess.client.Client;
import muscaa.chess.client.gui.ChildGuiScreen;
import muscaa.chess.client.gui.GuiScreen;
import muscaa.chess.client.gui.widgets.WPanel;
import muscaa.chess.client.gui.widgets.WTable;
import muscaa.chess.client.gui.widgets.WTextButton;
import muscaa.chess.client.gui.widgets.WTextField;

public class ServerFormScreen extends ChildGuiScreen {
	
	private final String submitText;
	private final String name;
	private final String address;
	private final int port;
	private final VoidFunc3ObjObjInt<String, String> onSubmit;
	
	private WTextField nameField;
	private WTextField addressField;
	private WTextField portField;
	private WTextButton addButton;
	
	public ServerFormScreen(GuiScreen parent, String submitText, String name, String address, int port, VoidFunc3ObjObjInt<String, String> onSubmit) {
		super(parent);
		
		this.submitText = submitText;
		this.name = name;
		this.address = address;
		this.port = port;
		this.onSubmit = onSubmit;
	}
	
	public ServerFormScreen(GuiScreen parent, String submitText, VoidFunc3ObjObjInt<String, String> onSubmit) {
		this(parent, submitText, null, null, 0, onSubmit);
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
		addButton.setDisabled(!nameField.isInputValid() || !addressField.isInputValid() || !portField.isInputValid());
	}
	
	private WPanel form() {
		WTable content = new WTable();
		content.defaults().growX().pad(PAD_SMALL).minHeight(BUTTON_HEIGHT);
		
		nameField = new WTextField(name != null ? name : "Chess Server");
		nameField.setMessageText("Name");
		nameField.addActionListener(w -> update());
		nameField.addValidator(text -> !text.isBlank());
		content.add(nameField);
		content.row();
		
		addressField = new WTextField(address != null ? address : null);
		addressField.setMessageText("Address");
		addressField.addActionListener(w -> update());
		addressField.addValidator(text -> !text.isBlank());
		content.add(addressField);
		content.row();
		
		portField = new WTextField(port > 0 ? Integer.toString(port) : "40755");
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
		
		addButton = new WTextButton(submitText);
		addButton.addActionListener(w -> {
			onSubmit.invoke(nameField.getText(), addressField.getText(), Integer.parseInt(portField.getText()));
			Client.INSTANCE.guiLayer.setScreen(parent);
		});
		content.add(addButton);
		content.row();
		
		WTextButton cancelButton = new WTextButton("Cancel");
		cancelButton.addActionListener(w -> Client.INSTANCE.guiLayer.setScreen(parent));
		content.add(cancelButton);
		content.row();
		
		WPanel form = new WPanel();
		form.defaults().growX().pad(PAD_MEDIUM);
		form.add(content);
		
		return form;
	}
}
