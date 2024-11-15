package muscaa.chess.network.play.packets;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import fluff.bin.IBinaryInput;
import fluff.network.packet.IPacketInbound;
import muscaa.chess.shared.board.ChessCell;
import muscaa.chess.shared.board.ChessMove;

public class PacketSelectCell implements IPacketInbound {
	
	private ChessCell cell = new ChessCell();
	private List<ChessMove> moves = new LinkedList<>();
	
	public PacketSelectCell() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		in.Data(cell);
		
		int len = in.Int();
		for (int i = 0; i < len; i++) {
			ChessMove move = new ChessMove();
			in.Data(move);
			
			moves.add(move);
		}
	}
	
	public ChessCell getCell() {
		return cell;
	}
	
	public List<ChessMove> getMoves() {
		return moves;
	}
}
