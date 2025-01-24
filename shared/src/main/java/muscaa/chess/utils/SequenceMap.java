package muscaa.chess.utils;

import java.util.HashMap;

public class SequenceMap<V> extends HashMap<Integer, V> {
	
	private static final long serialVersionUID = 6861417634406443482L;
	private final Sequence sequence = new Sequence();
	
	@Override
	@Deprecated
	public V put(Integer key, V value) {
		return super.put(key, value);
	}
	
	public int put(V value) {
		int key = sequence.next();
		super.put(key, value);
		return key;
	}
	
	@Override
	public V remove(Object key) {
		if (!(key instanceof Integer i)) {
			throw new IllegalArgumentException("key must be an Integer");
		}
		
		sequence.release(i);
		return super.remove(i);
	}
	
	@Override
	public void clear() {
		sequence.reset();
		super.clear();
	}
}
