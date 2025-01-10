package muscaa.chess.config.base.values;

import muscaa.chess.config.base.BaseValue;
import muscaa.chess.config.values.IValueShort;

public abstract class BaseValueShort<C> extends BaseValue<IValueShort, C> implements IValueShort {
	
	public short defaultValue;
	public short value;
	
	public BaseValueShort(String key, short defaultValue, short value) {
		super(key);
		
		this.defaultValue = defaultValue;
		
		set(value);
	}
	
	@Override
	public boolean isSet() {
		return true;
	}
	
	@Override
	public short getDefault() {
		return defaultValue;
	}
	
	@Override
	public void setDefault() {
		set(getDefault());
	}
	
	@Override
	public short get() {
		return value;
	}
	
	@Override
	public void set(short value) {
		this.value = value;
		
		callListeners();
	}
}
