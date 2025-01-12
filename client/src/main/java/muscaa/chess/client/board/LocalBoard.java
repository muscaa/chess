package muscaa.chess.client.board;

import muscaa.chess.board.Cell;
import muscaa.chess.board.Lobby;
import muscaa.chess.board.player.AbstractPlayer;
import muscaa.chess.board.player.BotPlayer;
import muscaa.chess.client.Client;
import muscaa.chess.client.board.player.LocalPlayer;

public class LocalBoard extends AbstractBoard {
	
	protected final Lobby lobby;
	protected final AbstractPlayer player;
	protected final AbstractPlayer bot;
	
	public LocalBoard() {
		lobby = new Lobby();
		player = new LocalPlayer(this);
		bot = new BotPlayer();
	}
	
	@Override
	public void init(Client chess, BoardLayer layer) {
		super.init(chess, layer);
		
		lobby.join(player, false);
		lobby.join(bot, false);
		
		Client.INSTANCE.setScreen(null);
	}
	
	@Override
	public void click(Cell cell) {
		player.click(cell);
	}
	
	@Override
	public void dispose() {
		lobby.close();
	}
}
