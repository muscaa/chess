package muscaa.chess.server.network.play.packet;

import java.io.IOException;

import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacketOutbound;
import muscaa.chess.server.board.AbstractServerChessPiece;
import muscaa.chess.shared.board.ChessCell;

public class PacketMove implements IPacketOutbound {
	
	private ChessCell from;
	private AbstractServerChessPiece fromPiece;
	private ChessCell to;
	private AbstractServerChessPiece toPiece;
	private AbstractServerChessPiece capturePiece;
	private ChessCell checkCell;
	
	public PacketMove(ChessCell from, AbstractServerChessPiece fromPiece, ChessCell to, AbstractServerChessPiece toPiece, AbstractServerChessPiece capturePiece, ChessCell checkCell) {
		this.from = from.copy();
		this.fromPiece = fromPiece;
		this.to = to.copy();
		this.toPiece = toPiece;
		this.capturePiece = capturePiece;
		this.checkCell = checkCell.copy();
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		out.Data(from);
		out.Int(fromPiece.getID());
		
		out.Data(to);
		out.Int(toPiece.getID());
		
		out.Int(capturePiece.getID());
		
		out.Data(checkCell);
	}
}
