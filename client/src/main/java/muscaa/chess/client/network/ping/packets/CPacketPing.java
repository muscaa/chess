package muscaa.chess.client.network.ping.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.network.packet.IPacketInbound;

public class CPacketPing implements IPacketInbound {
	
	private int players;
	private int maxPlayers;
	private String line1;
	private String line2;
	
	public CPacketPing() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		players = in.Int();
		maxPlayers = in.Int();
		line1 = in.LenString();
		line2 = in.LenString();
	}
	
	public int getPlayers() {
		return players;
	}
	
	public int getMaxPlayers() {
		return maxPlayers;
	}
	
	public String getLine1() {
		return line1;
	}
	
	public String getLine2() {
		return line2;
	}
}
