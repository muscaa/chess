package muscaa.chess.config.base.values;

import muscaa.chess.config.base.BaseValue;
import muscaa.chess.config.values.IValueObject;

public abstract class BaseValueObject<C, V> extends BaseValue<IValueObject<V>, C> implements IValueObject<V> {
	
	public V defaultValue;
	public V value;
	
	public BaseValueObject(String key, V defaultValue, V value) {
		super(key);
		
		this.defaultValue = defaultValue;
		
		set(value);
	}
	
	@Override
	public boolean isSet() {
		return value != null;
	}
	
	@Override
	public V getDefault() {
		return defaultValue;
	}
	
	@Override
	public void setDefault() {
		set(getDefault());
	}
	
	@Override
	public V get() {
		return value;
	}
	
	@Override
	public void set(V value) {
		this.value = value;
		
		callListeners();
	}
}
