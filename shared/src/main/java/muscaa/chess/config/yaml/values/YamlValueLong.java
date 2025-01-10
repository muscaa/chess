package muscaa.chess.config.yaml.values;

import java.util.Map;

import muscaa.chess.config.base.values.BaseValueLong;

public class YamlValueLong extends BaseValueLong<Map<String, Object>> {
	
	public YamlValueLong(String key, long defaultValue, long value) {
		super(key, defaultValue, value);
	}
	
	@Override
	public void load(Map<String, Object> context) {
		if (context.containsKey(key)) {
			set(((Number) context.get(key)).longValue());
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
