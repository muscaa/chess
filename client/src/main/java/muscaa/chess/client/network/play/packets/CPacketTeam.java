package muscaa.chess.client.network.play.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.network.packet.IPacketInbound;
import muscaa.chess.board.TeamRegistry;
import muscaa.chess.board.TeamValue;
import muscaa.chess.network.PacketInputUtils;

public class CPacketTeam implements IPacketInbound {
	
	private TeamValue team;
	
	public CPacketTeam() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		team = PacketInputUtils.regEntry(in, TeamRegistry.REG);
	}
	
	public TeamValue getTeam() {
		return team;
	}
}
