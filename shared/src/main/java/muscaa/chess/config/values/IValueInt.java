package muscaa.chess.config.values;

import muscaa.chess.config.IValue;

public interface IValueInt extends IValue<IValueInt> {

	int getDefault();
	
	int get();
	
	void set(int value);
}
