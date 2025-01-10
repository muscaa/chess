package muscaa.chess.client.network.play.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.network.packet.IPacketInbound;
import muscaa.chess.board.TeamRegistry;
import muscaa.chess.board.TeamValue;
import muscaa.chess.network.PacketInputUtils;

public class CPacketGameEnd implements IPacketInbound {
	
	private TeamValue winner;
	
	public CPacketGameEnd() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		winner = PacketInputUtils.regEntry(in, TeamRegistry.REG);
	}
	
	public TeamValue getWinner() {
		return winner;
	}
}
