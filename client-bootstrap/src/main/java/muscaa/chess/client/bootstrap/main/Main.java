package muscaa.chess.client.bootstrap.main;

import muscaa.chess.client.bootstrap.Bootstrap;
import muscaa.chess.client.bootstrap.DefaultBootstrap;

public class Main {
	
	public static void main(String[] args) throws Exception {
		Bootstrap.INSTANCE = new DefaultBootstrap();
		Bootstrap.INSTANCE.launch(args);
	}
}
