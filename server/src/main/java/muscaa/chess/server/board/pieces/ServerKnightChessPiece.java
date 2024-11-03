package muscaa.chess.server.board.pieces;

import muscaa.chess.server.board.AbstractServerChessPiece;
import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.shared.board.IChessPiece;

public class ServerKnightChessPiece extends AbstractServerChessPiece {
	
	public ServerKnightChessPiece(ChessColor color) {
		super(4, color);
	}
	
	@Override
	public IChessPiece copy() {
		return null;
	}
}
