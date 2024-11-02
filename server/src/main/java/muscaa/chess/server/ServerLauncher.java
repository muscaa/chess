package muscaa.chess.server;

public class ServerLauncher {
	
    public static void main(String[] args) throws Exception {
    	GameServer.INSTANCE.start();
    }
}