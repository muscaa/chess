package muscaa.chess.client.mod;

public interface IClientModInitializer {
	
	void onPreInitializeClient() throws Exception;
	
	void onPostInitializeClient() throws Exception;
}
