package muscaa.chess.network.play.packets;

import java.io.IOException;

import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacketOutbound;
import muscaa.chess.board.TeamValue;
import muscaa.chess.network.PacketOutputUtils;

public class SPacketTeam implements IPacketOutbound {
	
	private TeamValue team;
	
	public SPacketTeam(TeamValue team) {
		this.team = team;
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		PacketOutputUtils.regEntry(out, team);
	}
}
