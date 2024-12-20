package muscaa.chess.network.play.packets;

import java.io.IOException;

import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacketOutbound;
import muscaa.chess.shared.board.Cell;
import muscaa.chess.shared.network.PacketOutputUtils;

public class CPacketClickCell implements IPacketOutbound {
	
	private Cell cell;
	
	public CPacketClickCell(Cell cell) {
		this.cell = cell.copy();
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		PacketOutputUtils.cell(out, cell);
	}
}
