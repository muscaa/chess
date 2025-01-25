package muscaa.chess.form.field;

public class FormField {
	
	public String id;
	public FormFieldValue fieldType;
	public String name;
	
	public FormField(String id, FormFieldValue fieldType, String name) {
		this.id = id;
        this.fieldType = fieldType;
        this.name = name;
	}
	
	public <V> V get(FormFieldData fieldData) {
		return getOr(fieldData, null);
	}
	
	public <V> V getOr(FormFieldData fieldData, V defaultValue) {
		try {
			return getException(fieldData);
		} catch (Exception e) {}
		return defaultValue;
	}
	
	public <V> V getException(FormFieldData fieldData) throws Exception {
		return fieldType.parse(fieldData.value);
	}
	
	public boolean isValid(FormFieldData fieldData) {
		if (!id.equals(fieldData.id)) return false;
		
		try {
			getException(fieldData);
			
			return true;
		} catch (Exception e) {}
		
		return false;
	}
	
	public FormField copy() {
		return new FormField(id, fieldType, name);
	}
}
