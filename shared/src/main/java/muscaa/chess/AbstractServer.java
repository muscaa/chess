package muscaa.chess;

import java.util.Iterator;
import java.util.LinkedList;

import com.mojang.brigadier.exceptions.CommandSyntaxException;

import muscaa.chess.board.AbstractServerBoard;
import muscaa.chess.chat.ChatUtils;
import muscaa.chess.command.CommandRegistry;
import muscaa.chess.network.DisconnectReasonRegistry;
import muscaa.chess.player.AbstractServerPlayer;
import muscaa.chess.utils.SequenceMap;

public abstract class AbstractServer {
	
	protected static AbstractServer INSTANCE;
	
	private final SequenceMap<AbstractServerBoard> boards = new SequenceMap<>();
	private final LinkedList<AbstractServerPlayer> players = new LinkedList<>();
	
	public void onSendChatMessage(AbstractServerPlayer player, String message) {
		if (message.startsWith("/")) {
			try {
				CommandRegistry.DISPATCHER.execute(message.substring(1), player);
			} catch (CommandSyntaxException e) {
				player.addChatLine(e.getMessage());
			}
			return;
		}
		
		broadcast(ChatUtils.format("&7[&6" + player.getName() + "&7] &f") + message);
	}
	
	public void broadcast(String message) {
		for (AbstractServerPlayer p : players) {
			p.addChatLine(message);
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
		player.init(this);
	}
	
	public void removePlayer(AbstractServerPlayer player) {
		if (player.getBoard() != null) {
			player.getBoard().leave(player);
		}
		
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
	
	public static AbstractServer getInstance() {
		return INSTANCE;
	}
}
