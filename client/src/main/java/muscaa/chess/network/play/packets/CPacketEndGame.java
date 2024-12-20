package muscaa.chess.network.play.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.network.packet.IPacketInbound;
import muscaa.chess.shared.board.Team;
import muscaa.chess.shared.network.PacketInputUtils;
import muscaa.chess.shared.registry.registries.TeamRegistry;

public class CPacketEndGame implements IPacketInbound {
	
	private Team winner;
	
	public CPacketEndGame() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		winner = PacketInputUtils.regEntry(in, TeamRegistry.REG);
	}
	
	public Team getWinner() {
		return winner;
	}
}
