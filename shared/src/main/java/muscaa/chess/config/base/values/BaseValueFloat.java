package muscaa.chess.config.base.values;

import muscaa.chess.config.base.BaseValue;
import muscaa.chess.config.values.IValueFloat;

public abstract class BaseValueFloat<C> extends BaseValue<IValueFloat, C> implements IValueFloat {
	
	public float defaultValue;
	public float value;
	
	public BaseValueFloat(String key, float defaultValue, float value) {
		super(key);
		
		this.defaultValue = defaultValue;
		
		set(value);
	}
	
	@Override
	public boolean isSet() {
		return true;
	}
	
	@Override
	public float getDefault() {
		return defaultValue;
	}
	
	@Override
	public void setDefault() {
		set(getDefault());
	}
	
	@Override
	public float get() {
		return value;
	}
	
	@Override
	public void set(float value) {
		this.value = value;
		
		callListeners();
	}
}
