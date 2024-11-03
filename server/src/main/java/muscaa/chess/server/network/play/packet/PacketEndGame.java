package muscaa.chess.server.network.play.packet;

import java.io.IOException;

import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacketOutbound;
import muscaa.chess.shared.board.ChessColor;

public class PacketEndGame implements IPacketOutbound {
	
	private ChessColor winner;
	
	public PacketEndGame(ChessColor winner) {
		this.winner = winner;
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		out.Int(winner.getID());
	}
}
