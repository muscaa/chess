package muscaa.chess.config.base.values;

import muscaa.chess.config.base.BaseValue;
import muscaa.chess.config.values.IValueDouble;

public abstract class BaseValueDouble<C> extends BaseValue<IValueDouble, C> implements IValueDouble {
	
	public double defaultValue;
	public double value;
	
	public BaseValueDouble(String key, double defaultValue, double value) {
		super(key);
		
		this.defaultValue = defaultValue;
		
		set(value);
	}
	
	@Override
	public boolean isSet() {
		return true;
	}
	
	@Override
	public double getDefault() {
		return defaultValue;
	}
	
	@Override
	public void setDefault() {
		set(getDefault());
	}
	
	@Override
	public double get() {
		return value;
	}
	
	@Override
	public void set(double value) {
		this.value = value;
		
		callListeners();
	}
}
