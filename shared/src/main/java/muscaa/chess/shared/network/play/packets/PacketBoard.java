package muscaa.chess.shared.network.play.packets;

import java.io.IOException;

import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacketOutbound;
import muscaa.chess.shared.board.Cell;
import muscaa.chess.shared.board.matrix.Matrix;
import muscaa.chess.shared.board.piece.AbstractPiece;
import muscaa.chess.shared.network.PacketOutputUtils;

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
			AbstractPiece piece = matrix.get(cell);
			
			PacketOutputUtils.piece(out, piece);
		}
	}
}
