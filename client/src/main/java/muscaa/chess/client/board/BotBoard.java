package muscaa.chess.client.board;

import muscaa.chess.board.player.AbstractPlayer;
import muscaa.chess.board.player.BotPlayer;

public class BotBoard extends AbstractLocalBoard {
	
	protected final AbstractPlayer bot;
	
	public BotBoard() {
		bot = new BotPlayer();
	}
	
	@Override
	protected void addPlayers() {
		super.addPlayers();
		
		lobby.join(bot, false);
	}
}
