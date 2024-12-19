package muscaa.chess.shared.network.play.packets;

import java.io.IOException;

import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacketOutbound;
import muscaa.chess.shared.board.Team;
import muscaa.chess.shared.network.PacketOutputUtils;

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
