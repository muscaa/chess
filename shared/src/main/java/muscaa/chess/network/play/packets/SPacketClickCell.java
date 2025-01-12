package muscaa.chess.network.play.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.network.packet.IPacketInbound;
import muscaa.chess.board.Cell;
import muscaa.chess.network.PacketInputUtils;

public class SPacketClickCell implements IPacketInbound {
	
	private Cell cell;
	
	public SPacketClickCell() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		cell = PacketInputUtils.cell(in);
	}
	
	public Cell getCell() {
		return cell;
	}
}
