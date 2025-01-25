package muscaa.chess.form.button;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.bin.IBinaryOutput;
import muscaa.chess.form.AbstractFormWidgetData;

public class FormButtonWidgetData extends AbstractFormWidgetData {
	
	public boolean pressed;
	
	public FormButtonWidgetData(String id, boolean pressed) {
		super(id);
		
		this.pressed = pressed;
	}
	
	public FormButtonWidgetData() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		super.readData(in);
		
		pressed = in.Boolean();
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		super.writeData(out);
		
		out.Boolean(pressed);
	}
}
