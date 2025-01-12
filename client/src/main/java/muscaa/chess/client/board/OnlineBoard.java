package muscaa.chess.client.board;

import java.io.IOException;
import java.net.UnknownHostException;

import fluff.network.NetworkException;
import muscaa.chess.board.Cell;
import muscaa.chess.client.config.ServersConfig;
import muscaa.chess.client.network.ChessClient;
import muscaa.chess.client.network.play.packets.CPacketClickCell;

public class OnlineBoard extends AbstractBoard {
	
	protected final ChessClient networkClient;
    
    public OnlineBoard(ServersConfig.Server server) throws UnknownHostException, IOException, NetworkException {
    	networkClient = new ChessClient();
    	networkClient.connect(server);
	}
    
	@Override
	public void click(Cell cell) {
		networkClient.send(new CPacketClickCell(cell));
	}
	
	@Override
	public void dispose() {
		super.dispose();
		
		networkClient.disconnect();	
	}
}
