package muscaa.chess.client.gui.screens;

import fluff.functions.gen.obj.obj.VoidFunc2;
import muscaa.chess.client.gui.GuiScreen;
import muscaa.chess.client.gui.widgets.WPanel;
import muscaa.chess.client.gui.widgets.WTable;
import muscaa.chess.client.gui.widgets.WTextButton;
import muscaa.chess.client.gui.widgets.WTextField;
import muscaa.chess.form.Form;
import muscaa.chess.form.FormData;
import muscaa.chess.form.field.FormField;
import muscaa.chess.form.field.FormFieldData;

public class FormScreen extends GuiScreen {
	
	private final Form form;
	private final VoidFunc2<Form, FormData> onSubmit;
	
	private FormData formData;
	private WTextButton submitButton;
	
	public FormScreen(Form form, VoidFunc2<Form, FormData> onSubmit) {
		this.form = form;
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
		boolean valid = form.isValid(formData);
		
		submitButton.setDisabled(!valid);
	}
	
	private WPanel form() {
		WTable content = new WTable();
		content.defaults().growX().pad(PAD_SMALL).minHeight(BUTTON_HEIGHT);
		
		formData = new FormData(form.id);
		for (FormField field : form) {
			formData.add(new FormFieldData(field.id, ""));
			
			WTextField textField = new WTextField();
			textField.setMessageText(field.name);
			textField.addActionListener(w -> {
				formData.get(field.id).value = textField.getText();
				
				update();
			});
			textField.addValidator(text -> {
				try {
					field.fieldType.parse(text);
					return true;
				} catch (Exception e) {}
				return false;
			});
			content.add(textField);
			content.row();
		}
		
		content.add();
		content.row();
		
		submitButton = new WTextButton(form.submitText);
		submitButton.addActionListener(w -> {
			onSubmit.invoke(form, formData);
		});
		content.add(submitButton);
		content.row();
		
		WTextButton mainMenuButton = new WTextButton("Return to Main Menu");
		mainMenuButton.addActionListener(w -> chess.returnToMainMenu());
		content.add(mainMenuButton);
		content.row();
		
		WPanel form = new WPanel();
		form.defaults().growX().pad(PAD_MEDIUM);
		form.add(content);
		
		return form;
	}
}
