package muscaa.chess.server;

import muscaa.chess.shared.Server;

public class ServerLauncher {
	
    public static void main(String[] args) throws Exception {
    	Server.INSTANCE.start();
    }
}
