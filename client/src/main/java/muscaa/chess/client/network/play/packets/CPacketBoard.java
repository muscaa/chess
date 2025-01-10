package muscaa.chess.client.network.play.packets;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import fluff.bin.IBinaryInput;
import fluff.network.packet.IPacketInbound;
import muscaa.chess.client.board.ClientPiece;
import muscaa.chess.client.board.ClientPieceRegistry;
import muscaa.chess.network.PacketInputUtils;

public class CPacketBoard implements IPacketInbound {
	
	private int width;
	private int height;
	private List<ClientPiece> pieces;
	
	public CPacketBoard() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		width = in.Int();
		height = in.Int();
		
		pieces = new LinkedList<>();
		for (int i = 0; i < width * height; i++) {
			ClientPiece piece = PacketInputUtils.piece(in, ClientPieceRegistry.REG);
			
			pieces.add(piece);
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public List<ClientPiece> getPieces() {
		return pieces;
	}
}
