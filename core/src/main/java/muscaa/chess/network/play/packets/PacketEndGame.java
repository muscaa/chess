package muscaa.chess.network.play.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.network.packet.IPacketInbound;
import muscaa.chess.shared.board.ChessColor;

public class PacketEndGame implements IPacketInbound {
	
	private ChessColor winner;
	
	public PacketEndGame() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		winner = ChessColor.of(in.Int());
	}
	
	public ChessColor getWinner() {
		return winner;
	}
}
