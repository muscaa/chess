package muscaa.chess.network.play.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.network.packet.IPacketInbound;
import fluff.vecmath.gen._int.vector.Vec2i;
import muscaa.chess.board.ClientChessPiece;

public class PacketMove implements IPacketInbound {
	
	private Vec2i from;
	private ClientChessPiece fromPiece;
	private Vec2i to;
	private ClientChessPiece toPiece;
	private ClientChessPiece capturePiece;
	
	public PacketMove() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		from = new Vec2i(in.Int(), in.Int());
		fromPiece = ClientChessPiece.of(in.Int());
		
		to = new Vec2i(in.Int(), in.Int());
		toPiece = ClientChessPiece.of(in.Int());
		
		capturePiece = ClientChessPiece.of(in.Int());
	}
	
	public Vec2i getFrom() {
		return from;
	}
	
	public ClientChessPiece getFromPiece() {
		return fromPiece;
	}
	
	public Vec2i getTo() {
		return to;
	}
	
	public ClientChessPiece getToPiece() {
		return toPiece;
	}
	
	public ClientChessPiece getCapturePiece() {
		return capturePiece;
	}
}
