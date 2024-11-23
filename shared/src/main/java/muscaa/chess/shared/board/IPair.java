package muscaa.chess.shared.board;

public interface IPair<K, V> {
	
	K getKey();
	
	V getValue();
	
	static <K, V> IPair<K, V> of(K key, V value) {
		return new IPair<>() {
			
			@Override
			public K getKey() {
				return key;
			}
			
			@Override
			public V getValue() {
				return value;
			}
		};
	}
}
