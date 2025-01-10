package muscaa.chess.config.values;

import muscaa.chess.config.IValue;

public interface IValueBoolean extends IValue<IValueBoolean> {
	
	boolean getDefault();
	
	boolean get();
	
	void set(boolean value);
	
	void toggle();
}
