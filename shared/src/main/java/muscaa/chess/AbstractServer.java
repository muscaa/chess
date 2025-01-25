package muscaa.chess;

import java.util.Iterator;
import java.util.LinkedList;

import com.mojang.brigadier.exceptions.CommandSyntaxException;

import muscaa.chess.board.AbstractServerBoard;
import muscaa.chess.chat.ChatUtils;
import muscaa.chess.command.CommandRegistry;
import muscaa.chess.command.ConsoleCommandSource;
import muscaa.chess.command.ICommandSource;
import muscaa.chess.network.DisconnectReasonRegistry;
import muscaa.chess.player.AbstractServerPlayer;
import muscaa.chess.utils.SequenceMap;

public abstract class AbstractServer {
	
	protected static AbstractServer INSTANCE;
	
	protected final SequenceMap<AbstractServerBoard> boards = new SequenceMap<>();
	protected final LinkedList<AbstractServerPlayer> players = new LinkedList<>();
	protected final LinkedList<ICommandSource> sources = new LinkedList<>();
	protected final ConsoleCommandSource consoleSource;
	
	public AbstractServer() {
		this.consoleSource = new ConsoleCommandSource(this);
		
		sources.add(consoleSource);
	}
	
	public void onSendChatMessage(ICommandSource source, String message) {
		if (message.startsWith("/")) {
			try {
				CommandRegistry.DISPATCHER.execute(message.substring(1), source);
			} catch (CommandSyntaxException e) {
				source.addChatLine(ChatUtils.format("&c") + e.getMessage());
			}
			return;
		}
		
		broadcast(ChatUtils.format("&7[&6" + source.getName() + "&7] &f") + message);
	}
	
	public void broadcast(String message) {
		for (ICommandSource source : sources) {
			source.addChatLine(message);
		}
	}
	
	public void addBoard(AbstractServerBoard board) {
		int id = boards.put(board);
		board.init(this, id);
	}
	
	public void removeBoard(AbstractServerBoard board) {
		boards.remove(board.getID(), board);
	}
	
	public void addPlayer(AbstractServerPlayer player) {
		players.add(player);
		sources.add(player);
		player.init(this);
	}
	
	public void removePlayer(AbstractServerPlayer player) {
		if (player.getBoard() != null) {
			player.getBoard().leave(player);
		}
		
		sources.remove(player);
        players.remove(player);
	}
	
	public void start() {
		if (INSTANCE != null) {
			throw new IllegalStateException("Server is already running");
		}
		
		INSTANCE = this;
	}
	
	public void stop() {
		if (INSTANCE == null) {
			throw new IllegalStateException("Server is not running");
		}
		
		INSTANCE = null;
		
		Iterator<Integer> it = boards.keySet().iterator();
		while (it.hasNext()) {
			boards.get(it.next()).close();
		}
		
		for (AbstractServerPlayer p : players) {
			p.disconnect(DisconnectReasonRegistry.SERVER_STOP.get(), "Server stopped!");
		}
	}
	
	public int getBoardCount() {
		return boards.size();
	}
	
	public AbstractServerBoard getBoard(int id) {
		return boards.get(id);
	}
	
	public int getPlayerCount() {
		return players.size();
	}
	
	public int getMaxPlayerCount() {
		return 2;
	}
	
	public String[] getMotd() {
		return new String[] {
				ChatUtils.format("&7A Chess Server")
				};
	}
	
	public AbstractServerPlayer getPlayer(String name) {
		return players.stream()
				.filter(p -> p.getName().equals(name))
				.findFirst()
				.orElse(null);
	}
	
	public ConsoleCommandSource getConsoleSource() {
		return consoleSource;
	}
	
	public static AbstractServer getInstance() {
		return INSTANCE;
	}
}
