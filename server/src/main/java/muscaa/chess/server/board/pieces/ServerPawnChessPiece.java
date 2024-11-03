package muscaa.chess.server.board.pieces;

import muscaa.chess.server.board.AbstractServerChessPiece;
import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.shared.board.IChessPiece;

public class ServerPawnChessPiece extends AbstractServerChessPiece {
	
	public ServerPawnChessPiece(ChessColor color) {
		super(6, color);
	}
	
	@Override
	public IChessPiece copy() {
		return null;
	}
}
