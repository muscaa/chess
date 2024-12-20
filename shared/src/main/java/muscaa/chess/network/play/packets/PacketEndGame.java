package muscaa.chess.network.play.packets;

import java.io.IOException;

import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacketOutbound;
import muscaa.chess.board.Team;
import muscaa.chess.network.PacketOutputUtils;

public class PacketEndGame implements IPacketOutbound {
	
	private Team winner;
	
	public PacketEndGame(Team winner) {
		this.winner = winner;
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		PacketOutputUtils.regEntry(out, winner);
	}
}
