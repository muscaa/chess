package muscaa.chess.client.gui.screens;

import java.util.LinkedList;
import java.util.List;

import fluff.functions.gen.obj.obj.VoidFunc2;
import muscaa.chess.client.gui.GuiScreen;
import muscaa.chess.client.gui.widgets.WPanel;
import muscaa.chess.client.gui.widgets.WTable;
import muscaa.chess.client.gui.widgets.WTextButton;
import muscaa.chess.client.gui.widgets.WTextField;
import muscaa.chess.form.AbstractFormWidget;
import muscaa.chess.form.Form;
import muscaa.chess.form.FormData;
import muscaa.chess.form.button.FormButtonWidget;
import muscaa.chess.form.button.FormButtonWidgetData;
import muscaa.chess.form.field.FormFieldWidget;
import muscaa.chess.form.field.FormFieldWidgetData;

public class FormScreen extends GuiScreen {
	
	private final Form form;
	private final VoidFunc2<Form, FormData> onSubmit;
	
	private FormData formData;
	private List<WTextButton> textButtons;
	
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
		
		for (WTextButton textButton : textButtons) {
			textButton.setDisabled(!valid);
		}
	}
	
	private WPanel form() {
		WTable content = new WTable();
		content.defaults().growX().pad(PAD_SMALL).minHeight(BUTTON_HEIGHT);
		
		formData = new FormData(form.id);
		textButtons = new LinkedList<>();
		for (AbstractFormWidget widget : form) {
			if (widget instanceof FormButtonWidget button) {
				FormButtonWidgetData data = new FormButtonWidgetData(button.id, false);
				formData.add(data);
				
				WTextButton textButton = new WTextButton(button.name);
				textButton.addActionListener(w -> {
					data.pressed = true;
					
					onSubmit.invoke(form, formData);
				});
				textButtons.add(textButton);
				content.add(textButton);
				content.row();
			} else if (widget instanceof FormFieldWidget field) {
				FormFieldWidgetData data = new FormFieldWidgetData(field.id, "");
				formData.add(data);
				
				WTextField textField = new WTextField();
				textField.setMessageText(field.name);
				textField.addActionListener(w -> {
					data.value = textField.getText();
					
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
		}
		
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
