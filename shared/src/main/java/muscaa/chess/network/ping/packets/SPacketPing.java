package muscaa.chess.network.ping.packets;

import java.io.IOException;

import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacketOutbound;

public class SPacketPing implements IPacketOutbound {
	
	private int players;
	private int maxPlayers;
	private String line1;
	private String line2;
	
	public SPacketPing(int players, int maxPlayers, String line1, String line2) {
		this.players = players;
		this.maxPlayers = maxPlayers;
		this.line1 = line1;
		this.line2 = line2;
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		out.Int(players);
		out.Int(maxPlayers);
		out.LenString(line1);
		out.LenString(line2);
	}
}
