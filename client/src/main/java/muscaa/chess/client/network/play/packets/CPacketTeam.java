package muscaa.chess.client.network.play.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.network.packet.IPacketInbound;
import muscaa.chess.board.Team;
import muscaa.chess.network.PacketInputUtils;
import muscaa.chess.registry.registries.TeamRegistry;

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
