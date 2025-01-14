package muscaa.chess.form.field;

import fluff.functions.gen.obj.TFunc1;
import muscaa.chess.registry.IRegistryValue;
import muscaa.chess.registry.RegistryKey;

public class FormFieldValue implements IRegistryValue<FormFieldValue> {
	
	private final RegistryKey<FormFieldValue> key;
	private final TFunc1<?, String, Exception> parseFunc;
	
	public FormFieldValue(RegistryKey<FormFieldValue> key, TFunc1<?, String, Exception> parseFunc) {
		this.key = key;
		this.parseFunc = parseFunc;
	}
	
	public <V> V parse(String value) throws Exception {
		return (V) parseFunc.invoke(value);
	}
	
	@Override
	public RegistryKey<FormFieldValue> getKey() {
		return key;
	}
}
