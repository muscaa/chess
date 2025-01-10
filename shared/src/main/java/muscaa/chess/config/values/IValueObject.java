package muscaa.chess.config.values;

import muscaa.chess.config.IValue;

public interface IValueObject<V> extends IValue<IValueObject<V>> {

	V getDefault();
	
	V get();
	
	void set(V value);
}
