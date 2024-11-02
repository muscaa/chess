package muscaa.chess.network.play.packets;

import java.io.IOException;

import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacketOutbound;
import fluff.vecmath.gen._int.vector.Vec2i;

public class PacketClickCell implements IPacketOutbound {
	
	private Vec2i cell;
	
	public PacketClickCell(Vec2i cell) {
		this.cell = cell.copy();
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		out.Int(cell.x);
		out.Int(cell.y);
	}
}
