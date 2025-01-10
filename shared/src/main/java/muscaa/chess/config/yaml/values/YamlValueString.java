package muscaa.chess.config.yaml.values;

import java.util.Map;

import muscaa.chess.config.base.values.BaseValueString;

public class YamlValueString extends BaseValueString<Map<String, Object>> {
	
	public YamlValueString(String key, String defaultValue, String value) {
		super(key, defaultValue, value);
	}

	@Override
	public void load(Map<String, Object> context) {
		if (context.containsKey(key)) {
			set((String) context.get(key));
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
