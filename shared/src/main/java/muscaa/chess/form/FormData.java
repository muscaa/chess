package muscaa.chess.form;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import fluff.bin.IBinaryInput;
import fluff.bin.IBinaryOutput;
import fluff.bin.data.IBinaryData;

public class FormData implements IBinaryData, Iterable<AbstractFormWidgetData> {
	
	private final Map<String, AbstractFormWidgetData> widgets = new HashMap<>();
	public String id;
	
	public FormData(String id) {
		this.id = id;
	}
	
	public FormData() {}
	
	public int size() {
		return widgets.size();
	}
	
	public boolean contains(String id) {
        return widgets.containsKey(id);
	}
	
	public <V extends AbstractFormWidgetData> V get(String id) {
		return (V) widgets.get(id);
	}
	
	public void add(AbstractFormWidgetData widgetData) {
		widgets.put(widgetData.id, widgetData);
	}
	
	public void remove(String id) {
		widgets.remove(id);
	}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		id = in.LenString();
		
		int size = in.Int();
		for (int i = 0; i < size; i++) {
			int type = in.Int();
			
			AbstractFormWidgetData widgetData = FormWidgets.WIDGET_DATA_BY_TYPE.get(type).invoke();
			in.Data(widgetData);
			add(widgetData);
		}
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		out.LenString(id);
		
		out.Int(size());
		for (AbstractFormWidgetData widgetData : this) {
			int type = FormWidgets.WIDGET_DATA_TYPE.get(widgetData.getClass());
			out.Int(type);
			
			out.Data(widgetData);
		}
	}
	
	@Override
	public Iterator<AbstractFormWidgetData> iterator() {
		return widgets.values().iterator();
	}
}
