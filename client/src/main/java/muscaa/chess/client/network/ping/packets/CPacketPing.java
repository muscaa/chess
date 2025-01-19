package muscaa.chess.client.network.ping.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.network.packet.IPacketInbound;

public class CPacketPing implements IPacketInbound {
	
	private int players;
	private int maxPlayers;
	private String motd;
	
	public CPacketPing() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		players = in.Int();
		maxPlayers = in.Int();
		motd = in.LenString();
	}
	
	public int getPlayers() {
		return players;
	}
	
	public int getMaxPlayers() {
		return maxPlayers;
	}
	
	public String getMotd() {
		return motd;
	}
}
