package muscaa.chess.config.base.values;

import muscaa.chess.config.base.BaseValue;
import muscaa.chess.config.values.IValueByte;

public abstract class BaseValueByte<C> extends BaseValue<IValueByte, C> implements IValueByte {
	
	public byte defaultValue;
	public byte value;
	
	public BaseValueByte(String key, byte defaultValue, byte value) {
		super(key);
		
		this.defaultValue = defaultValue;
		
		set(value);
	}
	
	@Override
	public boolean isSet() {
		return true;
	}
	
	@Override
	public byte getDefault() {
		return defaultValue;
	}
	
	@Override
	public void setDefault() {
		set(getDefault());
	}
	
	@Override
	public byte get() {
		return value;
	}
	
	@Override
	public void set(byte value) {
		this.value = value;
		
		callListeners();
	}
}
