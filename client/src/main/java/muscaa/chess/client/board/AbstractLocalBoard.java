package muscaa.chess.client.board;

import muscaa.chess.board.Cell;
import muscaa.chess.board.Lobby;
import muscaa.chess.board.player.AbstractPlayer;
import muscaa.chess.client.Client;
import muscaa.chess.client.board.player.LocalPlayer;

public abstract class AbstractLocalBoard extends AbstractBoard {
	
	protected final Lobby lobby;
	protected final AbstractPlayer player;
	
	public AbstractLocalBoard() {
		lobby = new Lobby();
		player = new LocalPlayer(this);
	}
	
	protected void addPlayers() {
		lobby.join(player, false);
	}
	
	@Override
	public void init(Client chess, BoardLayer layer) {
		super.init(chess, layer);
		
		addPlayers();
		
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
