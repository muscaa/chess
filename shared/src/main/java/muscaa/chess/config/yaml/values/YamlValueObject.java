package muscaa.chess.config.yaml.values;

import java.util.Map;

import muscaa.chess.config.base.values.BaseValueObject;

public class YamlValueObject<V> extends BaseValueObject<Map<String, Object>, V> {
	
	public YamlValueObject(String key, V defaultValue, V value) {
		super(key, defaultValue, value);
	}
	
	@Override
	public void load(Map<String, Object> context) {
		if (context.containsKey(key)) {
			set((V) context.get(key));
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
