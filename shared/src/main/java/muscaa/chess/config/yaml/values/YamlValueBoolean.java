package muscaa.chess.config.yaml.values;

import java.util.Map;

import muscaa.chess.config.base.values.BaseValueBoolean;

public class YamlValueBoolean extends BaseValueBoolean<Map<String, Object>> {
	
	public YamlValueBoolean(String key, boolean defaultValue, boolean value) {
		super(key, defaultValue, value);
	}
	
	@Override
	public void load(Map<String, Object> context) {
		if (context.containsKey(key)) {
			set((Boolean) context.get(key));
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
