package muscaa.chess.config.values;

import muscaa.chess.config.IValue;

public interface IValueByte extends IValue<IValueByte> {
	
	byte getDefault();
	
	byte get();
	
	void set(byte value);
}
