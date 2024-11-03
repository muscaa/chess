package muscaa.chess.network.play.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.network.packet.IPacketInbound;
import muscaa.chess.board.ClientChessPiece;
import muscaa.chess.shared.board.ChessCell;
import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.shared.board.ChessPieceMatrix;

public class PacketStartGame implements IPacketInbound {
	
	private ChessColor color;
	private ChessPieceMatrix<ClientChessPiece> matrix;
	
	public PacketStartGame() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		color = ChessColor.of(in.Int());
		
		matrix = new ChessPieceMatrix<>();
		for (ChessCell cell : matrix) {
			ClientChessPiece piece = ClientChessPiece.of(in.Int());
			
			matrix.set(cell, piece);
		}
	}
	
	public ChessColor getColor() {
		return color;
	}
	
	public ChessPieceMatrix<ClientChessPiece> getMatrix() {
		return matrix;
	}
}
