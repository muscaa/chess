package muscaa.chess.command;

import muscaa.chess.AbstractServer;
import muscaa.chess.chat.ChatComponent;
import muscaa.chess.chat.ChatUtils;

public class ConsoleCommandSource implements ICommandSource {
	
	protected final AbstractServer server;
	
	public ConsoleCommandSource(AbstractServer server) {
		this.server = server;
	}
	
	@Override
	public void addChatLine(String line) {
		ChatComponent component = ChatUtils.parse(line);
		
		System.out.println(component.text());
	}
	
	@Override
	public String getName() {
		return "CONSOLE";
	}
	
	@Override
	public AbstractServer getServer() {
		return server;
	}
}
