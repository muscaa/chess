package muscaa.chess.config.yaml.values;

import java.util.Map;

import muscaa.chess.config.base.values.BaseValueInt;

public class YamlValueInt extends BaseValueInt<Map<String, Object>> {
	
	public YamlValueInt(String key, int defaultValue, int value) {
		super(key, defaultValue, value);
	}
	
	@Override
	public void load(Map<String, Object> context) {
		if (context.containsKey(key)) {
			set(((Number) context.get(key)).intValue());
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
