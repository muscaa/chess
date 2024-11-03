package muscaa.chess.server.board.pieces;

import muscaa.chess.server.board.AbstractServerChessPiece;
import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.shared.board.IChessPiece;

public class ServerEmptyChessPiece extends AbstractServerChessPiece {
	
	public static final ServerEmptyChessPiece INSTANCE = new ServerEmptyChessPiece();
	
	private ServerEmptyChessPiece() {
		super(0, ChessColor.NONE);
	}
	
	@Override
	public IChessPiece copy() {
		return INSTANCE;
	}
}
