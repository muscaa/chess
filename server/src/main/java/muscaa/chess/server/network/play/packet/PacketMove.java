package muscaa.chess.server.network.play.packet;

import java.io.IOException;

import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacketOutbound;
import fluff.vecmath.gen._int.vector.Vec2i;
import muscaa.chess.server.board.ServerChessPiece;

public class PacketMove implements IPacketOutbound {
	
	private Vec2i from;
	private Vec2i to;
	private ServerChessPiece piece;
	private ServerChessPiece capture;
	
	public PacketMove(Vec2i from, Vec2i to, ServerChessPiece piece, ServerChessPiece capture) {
		this.from = from;
		this.to = to;
		this.piece = piece;
		this.capture = capture;
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		out.Int(from.x);
		out.Int(from.y);
		
		out.Int(to.x);
		out.Int(to.y);
		
    	if (piece == null) {
    		out.Int(0);
    	} else {
    		out.LenString(piece.getID());
    	}
    	
    	if (capture == null) {
    		out.Int(0);
    	} else {
    		out.LenString(capture.getID());
    	}
	}
}
