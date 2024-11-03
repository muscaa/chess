package muscaa.chess.server.network.play.packet;

import java.io.IOException;

import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacketOutbound;
import fluff.vecmath.gen._int.vector.Vec2i;
import muscaa.chess.server.board.AbstractServerChessPiece;

public class PacketMove implements IPacketOutbound {
	
	private Vec2i from;
	private AbstractServerChessPiece fromPiece;
	private Vec2i to;
	private AbstractServerChessPiece toPiece;
	private AbstractServerChessPiece capturePiece;
	
	public PacketMove(Vec2i from, AbstractServerChessPiece fromPiece, Vec2i to, AbstractServerChessPiece toPiece, AbstractServerChessPiece capturePiece) {
		this.from = from;
		this.fromPiece = fromPiece;
		this.to = to;
		this.toPiece = toPiece;
		this.capturePiece = capturePiece;
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		out.Int(from.x);
		out.Int(from.y);
		
		out.Int(fromPiece.getID());
		
		out.Int(to.x);
		out.Int(to.y);
		
		out.Int(toPiece.getID());
		
		out.Int(capturePiece.getID());
	}
}
