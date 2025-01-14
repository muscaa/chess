package muscaa.chess.form;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import muscaa.chess.form.field.FormFieldData;

public class FormData implements Iterable<FormFieldData> {
	
	private final Map<String, FormFieldData> fields = new HashMap<>();
	public String id;
	
	public FormData(String id) {
		this.id = id;
	}
	
	public int size() {
		return fields.size();
	}
	
	public boolean contains(String id) {
        return fields.containsKey(id);
	}
	
	public FormFieldData get(String id) {
		return fields.get(id);
	}
	
	public void add(FormFieldData fieldData) {
		fields.put(fieldData.id, fieldData);
	}
	
	public void remove(String id) {
		fields.remove(id);
	}
	
	@Override
	public Iterator<FormFieldData> iterator() {
		return fields.values().iterator();
	}
}
