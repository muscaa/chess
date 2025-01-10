package muscaa.chess.config.yaml.values;

import java.util.Map;

import muscaa.chess.config.base.values.BaseValueByte;

public class YamlValueByte extends BaseValueByte<Map<String, Object>> {
	
	public YamlValueByte(String key, byte defaultValue, byte value) {
		super(key, defaultValue, value);
	}
	
	@Override
	public void load(Map<String, Object> context) {
		if (context.containsKey(key)) {
			set(((Number) context.get(key)).byteValue());
		} else {
			set(defaultValue);
		}
	}
	
	@Override
	public void save(Map<String, Object> context) {
		if (isSet()) {
			context.put(key, value);
		}
	}
}
