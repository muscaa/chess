package muscaa.chess.client.board;

import muscaa.chess.board.Cell;
import muscaa.chess.client.network.AbstractChessClient;
import muscaa.chess.client.network.play.packets.CPacketClickCell;

public class RemoteBoard extends AbstractBoard {
	
	protected final AbstractChessClient client;
    
    public RemoteBoard(AbstractChessClient client) {
    	this.client = client;
	}
    
	@Override
	public void click(Cell cell) {
		client.send(new CPacketClickCell(cell));
	}
	
	@Override
	public void dispose() {
		super.dispose();
		
		client.disconnect();
	}
}
