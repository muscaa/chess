package muscaa.chess.utils;

import java.util.LinkedList;
import java.util.Queue;

public class Sequence {
	
	private final Queue<Integer> stack = new LinkedList<>();
	private final int start;
	private int current;
	
	public Sequence() {
		this(0);
	}
	
	public Sequence(int start) {
		this.start = start;
		
        current = start;
	}
	
	public int next() {
		if (stack.isEmpty()) {
			return current++;
		}
		
		return stack.poll();
	}
	
	public void release(int value) {
		stack.add(value);
	}
	
	public void reset() {
		stack.clear();
		current = start;
	}
}
