package muscaa.chess.server.board.pieces;

import muscaa.chess.server.board.AbstractServerChessPiece;
import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.shared.board.IChessPiece;

public class ServerBishopChessPiece extends AbstractServerChessPiece {
	
	public ServerBishopChessPiece(ChessColor color) {
		super(3, color);
	}
	
	@Override
	public IChessPiece copy() {
		return null;
	}
}
