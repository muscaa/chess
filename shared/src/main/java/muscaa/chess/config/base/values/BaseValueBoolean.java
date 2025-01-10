package muscaa.chess.config.base.values;

import muscaa.chess.config.base.BaseValue;
import muscaa.chess.config.values.IValueBoolean;

public abstract class BaseValueBoolean<C> extends BaseValue<IValueBoolean, C> implements IValueBoolean {
	
	public boolean defaultValue;
	public boolean value;
	
	public BaseValueBoolean(String key, boolean defaultValue, boolean value) {
		super(key);
		
		this.defaultValue = defaultValue;
		
		set(value);
	}
	
	@Override
	public boolean isSet() {
		return true;
	}
	
	@Override
	public boolean getDefault() {
		return defaultValue;
	}
	
	@Override
	public void setDefault() {
		set(getDefault());
	}
	
	@Override
	public boolean get() {
		return value;
	}
	
	@Override
	public void set(boolean value) {
		this.value = value;
		
		callListeners();
	}
	
	@Override
	public void toggle() {
		set(!get());
	}
}
