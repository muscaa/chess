package muscaa.chess.network.play.packets;

import java.io.IOException;

import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacketOutbound;
import muscaa.chess.board.Cell;
import muscaa.chess.board.matrix.Matrix;
import muscaa.chess.board.piece.AbstractServerPiece;
import muscaa.chess.network.PacketOutputUtils;

public class PacketBoard implements IPacketOutbound {
	
	private Matrix matrix;
	
	public PacketBoard(Matrix matrix) {
		this.matrix = matrix;
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		out.Int(matrix.getWidth());
		out.Int(matrix.getHeight());
		
		for (Cell cell : matrix) {
			AbstractServerPiece piece = matrix.get(cell);
			
			PacketOutputUtils.piece(out, piece);
		}
	}
}
