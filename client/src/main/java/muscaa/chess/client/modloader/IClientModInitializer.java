package muscaa.chess.client.modloader;

public interface IClientModInitializer {
	
	void onPreInitializeClient() throws Exception;
	
	void onPostInitializeClient() throws Exception;
}
