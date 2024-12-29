package muscaa.chess.bootstrap.main;

import muscaa.chess.bootstrap.Bootstrap;
import muscaa.chess.bootstrap.DefaultBootstrap;

public class Main {
	
	public static void main(String[] args) throws Exception {
		String mainClass = System.getProperty("main.class");
		if (mainClass == null || mainClass.isBlank()) {
			throw new IllegalStateException("Property main.class not set!");
		}
		
		Bootstrap.INSTANCE = new DefaultBootstrap(mainClass);
		Bootstrap.INSTANCE.launch(args);
	}
}
