package muscaa.chess.network.play.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.network.packet.IPacketInbound;
import muscaa.chess.shared.board.Team;
import muscaa.chess.shared.network.PacketInputUtils;
import muscaa.chess.shared.registry.registries.TeamRegistry;

public class CPacketTeam implements IPacketInbound {
	
	private Team team;
	
	public CPacketTeam() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		team = PacketInputUtils.regEntry(in, TeamRegistry.REG);
	}
	
	public Team getTeam() {
		return team;
	}
}
