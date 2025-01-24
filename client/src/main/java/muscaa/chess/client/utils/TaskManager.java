package muscaa.chess.client.utils;

import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;

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
	
	public static void waitRender(VoidFunc func) {
		try {
			CompletableFuture<Void> future = new CompletableFuture<>();
			render(() -> {
				func.invoke();
				future.complete(null);
			});
			future.get();
		} catch (InterruptedException | ExecutionException e) {}
	}
}
