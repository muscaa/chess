package muscaa.chess.form;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.bin.IBinaryOutput;
import fluff.bin.data.IBinaryData;

public abstract class AbstractFormWidget<W extends AbstractFormWidget<W, D>, D extends AbstractFormWidgetData> implements IBinaryData {
	
	public String id;
	
	public AbstractFormWidget(String id) {
		this.id = id;
	}
	
	public AbstractFormWidget() {}
	
	public boolean isValid(D widgetData) {
		return id.equals(widgetData.id);
	}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		id = in.LenString();
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		out.LenString(id);
	}
	
	public abstract W copy();
}
