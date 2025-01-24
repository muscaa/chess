package muscaa.chess.client.board.boards;

import muscaa.chess.board.Cell;
import muscaa.chess.client.board.AbstractClientBoard;
import muscaa.chess.client.network.AbstractChessClient;
import muscaa.chess.client.network.play.packets.CPacketClickCell;

public class RemoteClientBoard extends AbstractClientBoard {
	
	protected final AbstractChessClient client;
	
	public RemoteClientBoard(AbstractChessClient client) {
		this.client = client;
	}
	
	@Override
	public void click(Cell cell) {
		client.send(new CPacketClickCell(cell));
	}
}
