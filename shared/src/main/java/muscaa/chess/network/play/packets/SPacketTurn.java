package muscaa.chess.network.play.packets;

import java.io.IOException;

import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacketOutbound;
import muscaa.chess.board.TeamValue;
import muscaa.chess.network.PacketOutputUtils;

public class SPacketTurn implements IPacketOutbound {
	
	private TeamValue turn;
	
	public SPacketTurn(TeamValue turn) {
		this.turn = turn;
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		PacketOutputUtils.regValue(out, turn);
	}
}
