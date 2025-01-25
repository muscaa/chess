package muscaa.chess.command;

import muscaa.chess.AbstractServer;

public interface ICommandSource {
	
	void addChatLine(String line);
	
	String getName();
	
	AbstractServer getServer();
}
