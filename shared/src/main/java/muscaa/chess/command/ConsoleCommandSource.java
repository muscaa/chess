package muscaa.chess.command;

public class ConsoleCommandSource implements ICommandSource {
	
	public static final ConsoleCommandSource INSTANCE = new ConsoleCommandSource();
	
	private ConsoleCommandSource() {}
	
	@Override
	public void addChatLine(String line) {
		System.out.println(line);
	}
	
	@Override
	public String getName() {
		return "CONSOLE";
	}
}
