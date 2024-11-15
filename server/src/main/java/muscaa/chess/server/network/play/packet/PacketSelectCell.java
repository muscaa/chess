package muscaa.chess.server.network.play.packet;

import java.io.IOException;
import java.util.List;

import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacketOutbound;
import muscaa.chess.shared.board.ChessCell;
import muscaa.chess.shared.board.ChessMove;

public class PacketSelectCell implements IPacketOutbound {
	
	private ChessCell cell;
	private List<ChessMove> moves;
	
	public PacketSelectCell(ChessCell cell, List<ChessMove> moves) {
		this.cell = cell.copy();
		this.moves = moves;
	}

	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		out.Data(cell);
		
		out.Int(moves.size());
		for (ChessMove move : moves) {
			out.Data(move);
		}
	}
}
