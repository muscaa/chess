package muscaa.chess.server.main;

import java.util.Scanner;

import com.mojang.brigadier.exceptions.CommandSyntaxException;

import muscaa.chess.Chess;
import muscaa.chess.command.CommandRegistry;
import muscaa.chess.command.ConsoleCommandSource;
import muscaa.chess.mod.ChessModLoader;
import muscaa.chess.mod.ModInfo;
import muscaa.chess.server.DedicatedServer;
import muscaa.chess.server.mod.IServerModInitializer;

public class Main {
	
	public static final ChessModLoader<IServerModInitializer> MOD_LOADER = new ChessModLoader<>(
			IServerModInitializer.class,
			ModInfo::getServerMain,
			IServerModInitializer::onPreInitializeServer,
			IServerModInitializer::onPostInitializeServer
			);
	
	public static DedicatedServer server;
	
    public static void main(String[] args) throws Exception {
    	MOD_LOADER.loadPre();
    	
    	Chess.init();
    	
    	server = new DedicatedServer();
    	
		server.server.start(true);
		
		MOD_LOADER.loadPost();
    	
		System.out.println("Server started. Type 'stop' to stop.");
		Scanner s = new Scanner(System.in);
		while (s.hasNextLine()) {
			String line = s.nextLine();
			if (line.equals("stop")) break;
			
			try {
				CommandRegistry.DISPATCHER.execute(line, ConsoleCommandSource.INSTANCE);
			} catch (CommandSyntaxException e) {
				e.printStackTrace();
			}
		}
		s.close();
		
		server.server.stop();
    }
}
