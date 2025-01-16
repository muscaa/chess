package muscaa.chess.mod;

public interface IClientModInitializer {
	
	void onPreInitializeClient() throws Exception;
	
	void onPostInitializeClient() throws Exception;
}
