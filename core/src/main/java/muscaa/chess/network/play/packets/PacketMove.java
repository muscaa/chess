package muscaa.chess.network.play.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.network.packet.IPacketInbound;
import muscaa.chess.board.ClientChessPiece;
import muscaa.chess.shared.board.ChessCell;

public class PacketMove implements IPacketInbound {
	
	private ChessCell from = new ChessCell();
	private ClientChessPiece fromPiece;
	private ChessCell to = new ChessCell();
	private ClientChessPiece toPiece;
	private ClientChessPiece capturePiece;
	private ChessCell checkCell = new ChessCell();
	
	public PacketMove() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		in.Data(from);
		fromPiece = ClientChessPiece.of(in.Int());
		
		in.Data(to);
		toPiece = ClientChessPiece.of(in.Int());
		
		capturePiece = ClientChessPiece.of(in.Int());
		
		in.Data(checkCell);
	}
	
	public ChessCell getFrom() {
		return from;
	}
	
	public ClientChessPiece getFromPiece() {
		return fromPiece;
	}
	
	public ChessCell getTo() {
		return to;
	}
	
	public ClientChessPiece getToPiece() {
		return toPiece;
	}
	
	public ClientChessPiece getCapturePiece() {
		return capturePiece;
	}
	
	public ChessCell getCheckCell() {
		return checkCell;
	}
}
