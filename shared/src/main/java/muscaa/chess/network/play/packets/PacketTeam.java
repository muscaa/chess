package muscaa.chess.network.play.packets;

import java.io.IOException;

import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacketOutbound;
import muscaa.chess.board.Team;
import muscaa.chess.network.PacketOutputUtils;

public class PacketTeam implements IPacketOutbound {
	
	private Team team;
	
	public PacketTeam(Team team) {
		this.team = team;
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		PacketOutputUtils.regEntry(out, team);
	}
}
