package muscaa.chess.config.base.values;

import muscaa.chess.config.base.BaseValue;
import muscaa.chess.config.values.IValueInt;

public abstract class BaseValueInt<C> extends BaseValue<IValueInt, C> implements IValueInt {
	
	public int defaultValue;
	public int value;
	
	public BaseValueInt(String key, int defaultValue, int value) {
		super(key);
		
		this.defaultValue = defaultValue;
		
		set(value);
	}
	
	@Override
	public boolean isSet() {
		return true;
	}
	
	@Override
	public int getDefault() {
		return defaultValue;
	}
	
	@Override
	public void setDefault() {
		set(getDefault());
	}
	
	@Override
	public int get() {
		return value;
	}
	
	@Override
	public void set(int value) {
		this.value = value;
		
		callListeners();
	}
}
