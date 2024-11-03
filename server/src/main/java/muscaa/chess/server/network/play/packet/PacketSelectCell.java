package muscaa.chess.server.network.play.packet;

import java.io.IOException;

import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacketOutbound;
import muscaa.chess.shared.board.ChessCell;

public class PacketSelectCell implements IPacketOutbound {
	
	private ChessCell cell;
	
	public PacketSelectCell(ChessCell cell) {
		this.cell = cell.copy();
	}

	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		out.Data(cell);
	}
}
