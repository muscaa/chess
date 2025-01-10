package muscaa.chess.config.values;

import muscaa.chess.config.IValue;

public interface IValueShort extends IValue<IValueShort> {

	short getDefault();
	
	short get();
	
	void set(short value);
}
