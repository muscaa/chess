package muscaa.chess.config.yaml.values;

import java.util.Map;

import muscaa.chess.config.base.values.BaseValueDouble;

public class YamlValueDouble extends BaseValueDouble<Map<String, Object>> {
	
	public YamlValueDouble(String key, double defaultValue, double value) {
		super(key, defaultValue, value);
	}
	
	@Override
	public void load(Map<String, Object> context) {
		if (context.containsKey(key)) {
			set(((Number) context.get(key)).doubleValue());
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
