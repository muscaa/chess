package muscaa.chess.server.network.play.packet;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.network.packet.IPacketInbound;
import muscaa.chess.shared.board.ChessCell;

public class PacketClickCell implements IPacketInbound {
	
	private ChessCell cell;
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		cell = new ChessCell();
		in.Data(cell);
	}
	
	public ChessCell getCell() {
		return cell;
	}
}
