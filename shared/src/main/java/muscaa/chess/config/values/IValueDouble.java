package muscaa.chess.config.values;

import muscaa.chess.config.IValue;

public interface IValueDouble extends IValue<IValueDouble> {

	double getDefault();
	
	double get();
	
	void set(double value);
}
