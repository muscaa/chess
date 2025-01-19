package muscaa.chess.network.ping.packets;

import java.io.IOException;

import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacketOutbound;

public class SPacketPing implements IPacketOutbound {
	
	private int players;
	private int maxPlayers;
	private String motd;
	
	public SPacketPing(int players, int maxPlayers, String motd) {
		this.players = players;
		this.maxPlayers = maxPlayers;
		this.motd = motd;
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		out.Int(players);
		out.Int(maxPlayers);
		out.LenString(motd);
	}
}
