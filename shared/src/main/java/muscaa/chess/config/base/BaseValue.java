package muscaa.chess.config.base;

import java.util.ArrayList;
import java.util.List;

import fluff.functions.gen.obj.VoidFunc1;
import muscaa.chess.config.IValue;

public abstract class BaseValue<T extends IValue<T>, C> implements IValue<T> {
	
	public List<VoidFunc1<T>> listeners = new ArrayList<>();
	public String key;
	
	public BaseValue(String key) {
		this.key = key;
	}
	
	public abstract void load(C context);
	
	public abstract void save(C context);
	
	public void callListeners() {
		for (VoidFunc1<T> listener : listeners) {
			listener.invoke((T) this);
		}
	}
	
	@Override
	public T addListener(VoidFunc1<T> listener) {
		listeners.add(listener);
		return (T) this;
	}
	
	@Override
	public T removeListener(VoidFunc1<T> listener) {
		listeners.remove(listener);
		return (T) this;
	}
}
