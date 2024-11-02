package muscaa.chess.server.network.play.packet;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.network.packet.IPacketInbound;
import fluff.vecmath.gen._int.vector.Vec2i;

public class PacketClickCell implements IPacketInbound {
	
	private Vec2i cell;
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		cell = new Vec2i(in.Int(), in.Int());
	}
	
	public Vec2i getCell() {
		return cell;
	}
}
