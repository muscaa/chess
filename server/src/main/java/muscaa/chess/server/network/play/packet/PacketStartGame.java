package muscaa.chess.server.network.play.packet;

import java.io.IOException;

import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacketOutbound;
import fluff.vecmath.gen._int.vector.Vec2i;
import muscaa.chess.server.board.AbstractServerChessPiece;
import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.shared.board.ChessPieceMatrix;

public class PacketStartGame implements IPacketOutbound {
	
	private ChessColor color;
	private ChessPieceMatrix<AbstractServerChessPiece> matrix;
	
	public PacketStartGame(ChessColor color, ChessPieceMatrix<AbstractServerChessPiece> matrix) {
		this.color = color;
		this.matrix = matrix;
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		out.Int(color.getID());
		
		for (Vec2i cell : matrix) {
			AbstractServerChessPiece piece = matrix.get(cell);
			
			out.Int(piece.getID());
		}
	}
}
