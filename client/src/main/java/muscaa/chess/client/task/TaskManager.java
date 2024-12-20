package muscaa.chess.client.task;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import fluff.functions.gen.VoidFunc;

public class TaskManager {
	
	private static final Queue<VoidFunc> RENDER_QUEUE = new ConcurrentLinkedQueue<>();
	
	public static void executeRender() {
		VoidFunc func;
		while ((func = RENDER_QUEUE.poll()) != null) {
			func.invoke();
		}
	}
	
	public static void render(VoidFunc func) {
		RENDER_QUEUE.offer(func);
	}
}
