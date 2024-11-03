package muscaa.chess.server.board.pieces;

import muscaa.chess.server.board.AbstractServerChessPiece;
import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.shared.board.IChessPiece;

public class ServerRookChessPiece extends AbstractServerChessPiece {
	
	public ServerRookChessPiece(ChessColor color) {
		super(5, color);
	}
	
	@Override
	public IChessPiece copy() {
		return null;
	}
}
