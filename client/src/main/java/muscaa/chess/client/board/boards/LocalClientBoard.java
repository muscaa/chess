package muscaa.chess.client.board.boards;

import muscaa.chess.board.Cell;
import muscaa.chess.client.board.AbstractClientBoard;
import muscaa.chess.player.AbstractServerPlayer;

public class LocalClientBoard extends AbstractClientBoard {
	
	public AbstractServerPlayer serverPlayer;
	
	@Override
	public void click(Cell cell) {
		serverPlayer.onClick(cell);
	}
}
