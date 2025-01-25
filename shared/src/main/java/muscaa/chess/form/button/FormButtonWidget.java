package muscaa.chess.form.button;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.bin.IBinaryOutput;
import muscaa.chess.form.AbstractFormWidget;

public class FormButtonWidget extends AbstractFormWidget<FormButtonWidget, FormButtonWidgetData> {
	
	public String name;
	
	public FormButtonWidget(String id, String name) {
		super(id);
		
        this.name = name;
	}
	
	public FormButtonWidget() {}
	
	public boolean isPressed(FormButtonWidgetData data) {
		return data.pressed;
	}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		super.readData(in);
		
		name = in.LenString();
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		super.writeData(out);
		
		out.LenString(name);
	}
	
	@Override
	public FormButtonWidget copy() {
		return new FormButtonWidget(id, name);
	}
}
