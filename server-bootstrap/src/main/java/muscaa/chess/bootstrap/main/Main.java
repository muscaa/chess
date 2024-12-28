package muscaa.chess.bootstrap.main;

import muscaa.chess.bootstrap.Bootstrap;
import muscaa.chess.bootstrap.DefaultBootstrap;

public class Main {
	
	public static void main(String[] args) throws Exception {
		System.out.println(System.in);
		System.out.println(System.console());
		
		Bootstrap.INSTANCE = new DefaultBootstrap("muscaa.chess.server.ServerLauncher");
		Bootstrap.INSTANCE.launch(args);
	}
}
