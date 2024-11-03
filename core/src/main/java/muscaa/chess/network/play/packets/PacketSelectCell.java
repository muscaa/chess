package muscaa.chess.network.play.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.network.packet.IPacketInbound;
import muscaa.chess.shared.board.ChessCell;

public class PacketSelectCell implements IPacketInbound {
	
	private ChessCell cell = new ChessCell();
	
	public PacketSelectCell() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		in.Data(cell);
	}
	
	public ChessCell getCell() {
		return cell;
	}
}
