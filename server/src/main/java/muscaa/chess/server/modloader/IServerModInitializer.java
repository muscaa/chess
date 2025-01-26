package muscaa.chess.server.modloader;

public interface IServerModInitializer {
	
	void onPreInitializeServer() throws Exception;
	
	void onPostInitializeServer() throws Exception;
}
