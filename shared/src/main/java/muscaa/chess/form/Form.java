package muscaa.chess.form;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import muscaa.chess.form.field.FormField;
import muscaa.chess.form.field.FormFieldData;

public class Form implements Iterable<FormField> {
	
	private final Map<String, FormField> fields = new LinkedHashMap<>();
	public String id;
	public String name;
	public String submitText;
	
	public Form(String id, String name, String submitText) {
        this.id = id;
        this.name = name;
        this.submitText = submitText;
	}
	
	public int size() {
		return fields.size();
	}
	
	public boolean contains(String id) {
        return fields.containsKey(id);
	}
	
	public FormField get(String id) {
		return fields.get(id);
	}
	
	public void add(FormField field) {
		fields.put(field.id, field);
	}
	
	public void remove(String id) {
		fields.remove(id);
	}
	
	public boolean isValid(FormData formData) {
		if (!id.equals(formData.id)) return false;
		if (size() != formData.size()) return false;
		
		for (FormField field : this) {
			FormFieldData fieldData = formData.get(field.id);
			if (fieldData == null) return false;
			
			if (!field.isValid(fieldData)) return false;
		}
		
		return true;
	}
	
	public Form copy() {
		Form copy = new Form(id, name, submitText);
		for (FormField field : this) {
            copy.add(field.copy());
		}
		return copy;
	}
	
	@Override
	public Iterator<FormField> iterator() {
		return fields.values().iterator();
	}
}
