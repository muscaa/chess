package muscaa.chess.network.login.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.network.packet.IPacketInbound;
import muscaa.chess.shared.board.ChessColor;

public class PacketLoginSuccess implements IPacketInbound {
	
	private ChessColor color;
	
	public PacketLoginSuccess() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		color = ChessColor.values()[in.Int()];
	}
	
	public ChessColor getColor() {
		return color;
	}
}
