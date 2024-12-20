package muscaa.chess.client.network.play.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.network.packet.IPacketInbound;
import muscaa.chess.board.Team;
import muscaa.chess.network.PacketInputUtils;
import muscaa.chess.registry.registries.TeamRegistry;

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
