package muscaa.chess.config;

import fluff.functions.gen.obj.VoidFunc1;

public interface IValue<T extends IValue<T>> {
	
	boolean isSet();
	
	void setDefault();
	
	T addListener(VoidFunc1<T> listener);
	
	T removeListener(VoidFunc1<T> listener);
}
