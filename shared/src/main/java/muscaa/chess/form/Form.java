package muscaa.chess.form;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import fluff.bin.IBinaryInput;
import fluff.bin.IBinaryOutput;
import fluff.bin.data.IBinaryData;

public class Form implements IBinaryData, Iterable<AbstractFormWidget> {
	
	private final Map<String, AbstractFormWidget> widgets = new LinkedHashMap<>();
	public String id;
	public String name;
	
	public Form(String id, String name) {
        this.id = id;
        this.name = name;
	}
	
	public Form() {}
	
	public int size() {
		return widgets.size();
	}
	
	public boolean contains(String id) {
        return widgets.containsKey(id);
	}
	
	public <V extends AbstractFormWidget> V get(String id) {
		return (V) widgets.get(id);
	}
	
	public void add(AbstractFormWidget widget) {
		widgets.put(widget.id, widget);
	}
	
	public void remove(String id) {
		widgets.remove(id);
	}
	
	public boolean isValid(FormData data) {
		if (!id.equals(data.id)) return false;
		if (size() != data.size()) return false;
		
		for (AbstractFormWidget widget : this) {
			AbstractFormWidgetData widgetData = data.get(widget.id);
			if (widgetData == null) return false;
			
			if (!widget.isValid(widgetData)) return false;
		}
		
		return true;
	}
	
	public Form copy() {
		Form copy = new Form(id, name);
		for (AbstractFormWidget widget : this) {
            copy.add(widget.copy());
		}
		return copy;
	}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		id = in.LenString();
		name = in.LenString();
		
		int size = in.Int();
		for (int i = 0; i < size; i++) {
			int type = in.Int();
			
			AbstractFormWidget widget = FormWidgets.WIDGET_BY_TYPE.get(type).invoke();
			in.Data(widget);
			add(widget);
		}
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		out.LenString(id);
		out.LenString(name);
		
		out.Int(size());
		for (AbstractFormWidget widget : this) {
			int type = FormWidgets.WIDGET_TYPE.get(widget.getClass());
			out.Int(type);
			
			out.Data(widget);
		}
	}
	
	@Override
	public Iterator<AbstractFormWidget> iterator() {
		return widgets.values().iterator();
	}
}
