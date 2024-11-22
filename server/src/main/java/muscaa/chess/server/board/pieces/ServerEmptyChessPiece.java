package muscaa.chess.server.board.pieces;

import muscaa.chess.server.board.AbstractServerChessPiece;
import muscaa.chess.shared.board.ChessCell;
import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.shared.board.ChessMoves;
import muscaa.chess.shared.board.IChessPiece;

public class ServerEmptyChessPiece extends AbstractServerChessPiece<ServerEmptyChessPiece> {
	
	public static final ServerEmptyChessPiece INSTANCE = new ServerEmptyChessPiece();
	
	private ServerEmptyChessPiece() {
		super(0, ChessColor.NONE, null);
	}
	
	@Override
	public void findMoves(ChessMoves<AbstractServerChessPiece> moves, ChessCell cell) {
	}
	
	@Override
	public void simulateMoves(ChessMoves<AbstractServerChessPiece> moves, ChessCell cell) {
	}
	
	@Override
	public IChessPiece copy() {
		return INSTANCE;
	}
}
