package muscaa.chess.network.play.packets;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import fluff.bin.IBinaryInput;
import fluff.network.packet.IPacketInbound;
import muscaa.chess.board.TexturedPiece;
import muscaa.chess.registries.TexturedPieceRegistry;
import muscaa.chess.shared.network.PacketInputUtils;

public class CPacketBoard implements IPacketInbound {
	
	private int width;
	private int height;
	private List<TexturedPiece> pieces;
	
	public CPacketBoard() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		width = in.Int();
		height = in.Int();
		
		pieces = new LinkedList<>();
		for (int i = 0; i < width * height; i++) {
			TexturedPiece piece = PacketInputUtils.piece(in, TexturedPieceRegistry.REG);
			
			pieces.add(piece);
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public List<TexturedPiece> getPieces() {
		return pieces;
	}
}
