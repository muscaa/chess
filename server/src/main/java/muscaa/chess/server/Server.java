package muscaa.chess.server;

import java.util.Scanner;

import muscaa.chess.Chess;
import muscaa.chess.mod.ChessModLoader;
import muscaa.chess.mod.ModException;
import muscaa.chess.mod.ModInfo;
import muscaa.chess.server.command.ServerCommandsInit;
import muscaa.chess.server.config.SettingsConfig;
import muscaa.chess.server.database.AbstractDatabase;
import muscaa.chess.server.database.DatabaseRegistry;
import muscaa.chess.server.database.impl.MainTables;
import muscaa.chess.server.hash.HashAlgorithmRegistry;
import muscaa.chess.server.mod.IServerModInitializer;
import muscaa.chess.utils.NamespacePath;

public class Server {
	
	public static final ChessModLoader<IServerModInitializer> MOD_LOADER = new ChessModLoader<>(
			IServerModInitializer.class,
			ModInfo::getServerMain,
			IServerModInitializer::onPreInitializeServer,
			IServerModInitializer::onPostInitializeServer
			);
	public static final Server INSTANCE = new Server();
	
	public final SettingsConfig settingsConfig;
	
	private final DedicatedServer dedicatedServer;
	private AbstractDatabase database;
	private MainTables mainTables;
	
	private boolean running;
	
	private Server() {
		try {
			MOD_LOADER.loadPre();
		} catch (ModException e) {
			throw new RuntimeException(e);
		}
		
		Chess.EVENTS.subscribe(new ServerCommandsInit());
		
		settingsConfig = new SettingsConfig();
		
		dedicatedServer = new DedicatedServer();
	}
	
	public void start() {
		running = true;
		
    	Chess.init();
    	
    	HashAlgorithmRegistry.init();
    	DatabaseRegistry.init();
    	
    	settingsConfig.load();
    	
    	database = DatabaseRegistry.REG.get(NamespacePath.of(settingsConfig.databaseType.get())).get().create();
    	mainTables = new MainTables();
    	
    	database.open();
		mainTables.init(database);
    	
    	dedicatedServer.start(settingsConfig.port.get());
		
		try {
			MOD_LOADER.loadPost();
		} catch (ModException e) {
			throw new RuntimeException(e);
		}
    	
		System.out.println("Server started. Type 'stop' to stop.");
		
		Scanner s = new Scanner(System.in);
		while (running && s.hasNextLine()) {
			String line = s.nextLine();
			
			dedicatedServer.onSendChatMessage(dedicatedServer.getConsoleSource(), "/" + line);
		}
		s.close();
		
		settingsConfig.save();
		dedicatedServer.stop();
		database.close();
	}
	
	public void stop() {
		running = false;
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public AbstractDatabase getDatabase() {
		return database;
	}
	
	public MainTables getMainTables() {
		return mainTables;
	}
	
	public DedicatedServer getDedicatedServer() {
		return dedicatedServer;
	}
}
