package muscaa.chess.config.values;

import muscaa.chess.config.IValue;

public interface IValueLong extends IValue<IValueLong> {

	long getDefault();
	
	long get();
	
	void set(long value);
}
