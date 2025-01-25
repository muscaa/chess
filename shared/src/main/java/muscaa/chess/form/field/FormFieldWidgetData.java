package muscaa.chess.form.field;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.bin.IBinaryOutput;
import muscaa.chess.form.AbstractFormWidgetData;

public class FormFieldWidgetData extends AbstractFormWidgetData {
	
	public String value;
	
	public FormFieldWidgetData(String id, String value) {
		super(id);
		
		this.value = value;
	}
	
	public FormFieldWidgetData() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		super.readData(in);
		
		value = in.LenString();
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		super.writeData(out);
		
		out.LenString(value);
	}
}
