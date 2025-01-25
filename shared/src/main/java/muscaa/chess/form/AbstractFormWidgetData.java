package muscaa.chess.form;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.bin.IBinaryOutput;
import fluff.bin.data.IBinaryData;

public abstract class AbstractFormWidgetData implements IBinaryData {
	
	public String id;
	
	public AbstractFormWidgetData(String id) {
		this.id = id;
	}
	
	public AbstractFormWidgetData() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		id = in.LenString();
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		out.LenString(id);
	}
}
