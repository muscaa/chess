package muscaa.chess.network.play.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.network.packet.IPacketInbound;
import muscaa.chess.board.ClientChessPiece;
import muscaa.chess.shared.board.IBoard;

public class PacketBoard implements IPacketInbound {
	
	private ClientChessPiece[][] pieces;
	
	public PacketBoard() {
		pieces = new ClientChessPiece[IBoard.SIZE][IBoard.SIZE];
	}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		for (int row = 0; row < IBoard.SIZE; row++) {
            for (int col = 0; col < IBoard.SIZE; col++) {
            	int len = in.Int();
            	
            	pieces[row][col] = ClientChessPiece.of(len == 0 ? null : in.String(len));
            }
        }
	}
	
	public ClientChessPiece[][] getPieces() {
		return pieces;
	}
}
