package muscaa.chess.server;

import java.util.Scanner;

import muscaa.chess.Chess;
import muscaa.chess.mod.ChessModLoader;
import muscaa.chess.mod.ModException;
import muscaa.chess.mod.ModInfo;
import muscaa.chess.server.command.ServerCommandsInit;
import muscaa.chess.server.mod.IServerModInitializer;

public class Server {
	
	public static final ChessModLoader<IServerModInitializer> MOD_LOADER = new ChessModLoader<>(
			IServerModInitializer.class,
			ModInfo::getServerMain,
			IServerModInitializer::onPreInitializeServer,
			IServerModInitializer::onPostInitializeServer
			);
	public static final Server INSTANCE = new Server();
	
	private boolean running;
	private DedicatedServer server;
	
	private Server() {
		try {
			MOD_LOADER.loadPre();
		} catch (ModException e) {
			throw new RuntimeException(e);
		}
		
		Chess.EVENTS.subscribe(new ServerCommandsInit());
	}
	
	public void start() {
		running = true;
		
    	Chess.init();
    	
    	// load configs
    	
    	server = new DedicatedServer(40755);
    	server.start();
		
		try {
			MOD_LOADER.loadPost();
		} catch (ModException e) {
			throw new RuntimeException(e);
		}
    	
		server.getConsoleSource().addChatLine("Server started. Type 'stop' to stop.");
		
		Scanner s = new Scanner(System.in);
		while (running && s.hasNextLine()) {
			String line = s.nextLine();
			
			server.onSendChatMessage(server.getConsoleSource(), "/" + line);
		}
		s.close();
		
		server.stop();
	}
	
	public void stop() {
		running = false;
	}
	
	public boolean isRunning() {
		return running;
	}
}
