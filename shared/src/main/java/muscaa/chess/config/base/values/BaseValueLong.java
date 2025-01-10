package muscaa.chess.config.base.values;

import muscaa.chess.config.base.BaseValue;
import muscaa.chess.config.values.IValueLong;

public abstract class BaseValueLong<C> extends BaseValue<IValueLong, C> implements IValueLong {
	
	public long defaultValue;
	public long value;
	
	public BaseValueLong(String key, long defaultValue, long value) {
		super(key);
		
		this.defaultValue = defaultValue;
		
		set(value);
	}
	
	@Override
	public boolean isSet() {
		return true;
	}
	
	@Override
	public long getDefault() {
		return defaultValue;
	}
	
	@Override
	public void setDefault() {
		set(getDefault());
	}
	
	@Override
	public long get() {
		return value;
	}
	
	@Override
	public void set(long value) {
		this.value = value;
		
		callListeners();
	}
}
