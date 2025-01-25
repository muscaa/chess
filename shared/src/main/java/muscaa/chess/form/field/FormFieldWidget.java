package muscaa.chess.form.field;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.bin.IBinaryOutput;
import muscaa.chess.form.AbstractFormWidget;
import muscaa.chess.network.PacketInputUtils;
import muscaa.chess.network.PacketOutputUtils;

public class FormFieldWidget extends AbstractFormWidget<FormFieldWidget, FormFieldWidgetData> {
	
	public FormFieldValue fieldType;
	public String name;
	
	public FormFieldWidget(String id, FormFieldValue fieldType, String name) {
		super(id);
		
        this.fieldType = fieldType;
        this.name = name;
	}
	
	public FormFieldWidget() {}
	
	public <V> V get(FormFieldWidgetData widgetData) {
		return getOr(widgetData, null);
	}
	
	public <V> V getOr(FormFieldWidgetData widgetData, V defaultValue) {
		try {
			return getException(widgetData);
		} catch (Exception e) {}
		return defaultValue;
	}
	
	public <V> V getException(FormFieldWidgetData widgetData) throws Exception {
		return fieldType.parse(widgetData.value);
	}
	
	@Override
	public boolean isValid(FormFieldWidgetData widgetData) {
		if (!super.isValid(widgetData)) return false;
		
		try {
			getException(widgetData);
			
			return true;
		} catch (Exception e) {}
		
		return false;
	}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		super.readData(in);
		
		fieldType = PacketInputUtils.regValue(in, FormFieldRegistry.REG);
		name = in.LenString();
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		super.writeData(out);
		
		PacketOutputUtils.regValue(out, fieldType);
		out.LenString(name);
	}
	
	@Override
	public FormFieldWidget copy() {
		return new FormFieldWidget(id, fieldType, name);
	}
}
