package muscaa.chess.form;

import java.util.HashMap;
import java.util.Map;

import fluff.functions.gen.Func;
import muscaa.chess.form.button.FormButtonWidget;
import muscaa.chess.form.button.FormButtonWidgetData;
import muscaa.chess.form.field.FormFieldRegistry;
import muscaa.chess.form.field.FormFieldWidget;
import muscaa.chess.form.field.FormFieldWidgetData;

public class FormWidgets {
	
	public static final Map<Class<? extends AbstractFormWidget>, Integer> WIDGET_TYPE = new HashMap<>();
	public static final Map<Integer, Func<? extends AbstractFormWidget>> WIDGET_BY_TYPE = new HashMap<>();
	
	public static final Map<Class<? extends AbstractFormWidgetData>, Integer> WIDGET_DATA_TYPE = new HashMap<>();
	public static final Map<Integer, Func<? extends AbstractFormWidgetData>> WIDGET_DATA_BY_TYPE = new HashMap<>();
	
	public static void init() {
		FormFieldRegistry.init();
		
		register(
				0,
				FormButtonWidget.class,
				FormButtonWidget::new,
				FormButtonWidgetData.class,
				FormButtonWidgetData::new
				);
		register(
				1,
				FormFieldWidget.class,
				FormFieldWidget::new,
				FormFieldWidgetData.class,
				FormFieldWidgetData::new
				);
	}
	
	public static void register(
			int id,
			Class<? extends AbstractFormWidget> widgetClass,
			Func<? extends AbstractFormWidget> widgetFunc,
			Class<? extends AbstractFormWidgetData> widgetDataClass,
			Func<? extends AbstractFormWidgetData> widgetDataFunc
			) {
		WIDGET_TYPE.put(widgetClass, id);
		WIDGET_BY_TYPE.put(id, widgetFunc);
		WIDGET_DATA_TYPE.put(widgetDataClass, id);
		WIDGET_DATA_BY_TYPE.put(id, widgetDataFunc);
	}
}
