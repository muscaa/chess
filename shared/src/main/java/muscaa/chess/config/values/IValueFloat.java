package muscaa.chess.config.values;

import muscaa.chess.config.IValue;

public interface IValueFloat extends IValue<IValueFloat> {

	float getDefault();
	
	float get();
	
	void set(float value);
}
