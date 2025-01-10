package muscaa.chess.config.base.values;

import muscaa.chess.config.values.IValueString;

public abstract class BaseValueString<C> extends BaseValueObject<C, String> implements IValueString {
	
	public BaseValueString(String key, String defaultValue, String value) {
		super(key, defaultValue, value);
	}
}
