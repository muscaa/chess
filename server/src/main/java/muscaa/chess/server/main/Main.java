package muscaa.chess.server.main;

import java.util.Scanner;

import com.mojang.brigadier.exceptions.CommandSyntaxException;

import muscaa.chess.Chess;
import muscaa.chess.Server;
import muscaa.chess.command.CommandRegistry;
import muscaa.chess.command.ICommandSource;

public class Main {
	
    public static void main(String[] args) throws Exception {
    	Chess.init();
    	
    	Server.INSTANCE.start();
    	
		System.out.println("Server started. Type 'stop' to stop.");
		Scanner s = new Scanner(System.in);
		while (s.hasNextLine()) {
			String line = s.nextLine();
			if (line.equals("stop")) break;
			
			try {
				CommandRegistry.DISPATCHER.execute(line, new ICommandSource() {});
			} catch (CommandSyntaxException e) {
				e.printStackTrace();
			}
		}
		s.close();
		
		Server.INSTANCE.stop();
    }
}
