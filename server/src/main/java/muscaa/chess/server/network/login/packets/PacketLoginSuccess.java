package muscaa.chess.server.network.login.packets;

import java.io.IOException;

import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacketOutbound;
import muscaa.chess.shared.board.ChessColor;

public class PacketLoginSuccess implements IPacketOutbound {
	
	private ChessColor color;
	
	public PacketLoginSuccess(ChessColor color) {
		this.color = color;
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		out.Int(color.ordinal());
	}
}
