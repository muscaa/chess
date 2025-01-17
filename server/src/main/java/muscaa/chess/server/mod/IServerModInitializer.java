package muscaa.chess.server.mod;

public interface IServerModInitializer {
	
	void onPreInitializeServer() throws Exception;
	
	void onPostInitializeServer() throws Exception;
}
