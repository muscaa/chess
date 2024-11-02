package muscaa.chess.server.network.play.packet;

import java.io.IOException;

import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacketOutbound;
import muscaa.chess.server.board.ServerChessPiece;
import muscaa.chess.shared.board.IBoard;

public class PacketBoard implements IPacketOutbound {
	
	private ServerChessPiece[][] pieces;
	
	public PacketBoard(ServerChessPiece[][] pieces) {
		this.pieces = new ServerChessPiece[IBoard.SIZE][IBoard.SIZE];
		for (int row = 0; row < IBoard.SIZE; row++) {
            for (int col = 0; col < IBoard.SIZE; col++) {
            	this.pieces[row][col] = pieces[row][col];
            }
        }
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		for (int row = 0; row < IBoard.SIZE; row++) {
            for (int col = 0; col < IBoard.SIZE; col++) {
            	ServerChessPiece piece = pieces[row][col];
            	if (piece == null) {
            		out.Int(0);
            	} else {
            		out.LenString(piece.getID());
            	}
            }
        }
	}
}
