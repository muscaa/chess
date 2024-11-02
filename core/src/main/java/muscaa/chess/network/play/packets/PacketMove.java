package muscaa.chess.network.play.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.network.packet.IPacketInbound;
import fluff.vecmath.gen._int.vector.Vec2i;
import muscaa.chess.board.ClientChessPiece;

public class PacketMove implements IPacketInbound {
	
	private Vec2i from;
	private Vec2i to;
	private ClientChessPiece piece;
	private ClientChessPiece capture;
	
	public PacketMove() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		from = new Vec2i(in.Int(), in.Int());
		to = new Vec2i(in.Int(), in.Int());
		
		int len = in.Int();
    	piece = ClientChessPiece.of(len == 0 ? null : in.String(len));
    	
    	len = in.Int();
    	capture = ClientChessPiece.of(len == 0 ? null : in.String(len));
	}
	
	public Vec2i getFrom() {
		return from;
	}
	
	public Vec2i getTo() {
		return to;
	}
	
	public ClientChessPiece getPiece() {
		return piece;
	}
	
	public ClientChessPiece getCapture() {
		return capture;
	}
}
