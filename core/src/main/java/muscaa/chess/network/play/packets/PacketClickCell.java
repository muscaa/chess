package muscaa.chess.network.play.packets;

import java.io.IOException;

import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacketOutbound;
import muscaa.chess.shared.board.ChessCell;

public class PacketClickCell implements IPacketOutbound {
	
	private ChessCell cell;
	
	public PacketClickCell(ChessCell cell) {
		this.cell = cell.copy();
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		out.Data(cell);
	}
}
