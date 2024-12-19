package muscaa.chess.shared.network.play.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.network.packet.IPacketInbound;
import muscaa.chess.shared.board.Cell;
import muscaa.chess.shared.network.PacketInputUtils;

public class PacketClickCell implements IPacketInbound {
	
	private Cell cell;
	
	public PacketClickCell() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		cell = PacketInputUtils.cell(in);
	}
	
	public Cell getCell() {
		return cell;
	}
}
