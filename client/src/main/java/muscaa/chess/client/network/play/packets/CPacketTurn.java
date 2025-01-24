package muscaa.chess.client.network.play.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.network.packet.IPacketInbound;
import muscaa.chess.board.TeamRegistry;
import muscaa.chess.board.TeamValue;
import muscaa.chess.network.PacketInputUtils;

public class CPacketTurn implements IPacketInbound {
	
	private TeamValue turn;
	
	public CPacketTurn() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		turn = PacketInputUtils.regValue(in, TeamRegistry.REG);
	}
	
	public TeamValue getTurn() {
		return turn;
	}
}
