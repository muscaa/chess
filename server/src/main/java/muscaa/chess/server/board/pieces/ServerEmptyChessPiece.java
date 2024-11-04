package muscaa.chess.server.board.pieces;

import muscaa.chess.server.board.AbstractServerChessPiece;
import muscaa.chess.shared.board.ChessCell;
import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.shared.board.ChessMoves;
import muscaa.chess.shared.board.ChessPieceMatrix;
import muscaa.chess.shared.board.IChessPiece;

public class ServerEmptyChessPiece extends AbstractServerChessPiece {
	
	public static final ServerEmptyChessPiece INSTANCE = new ServerEmptyChessPiece();
	
	private ServerEmptyChessPiece() {
		super(0, ChessColor.NONE);
	}
	
	@Override
	public ChessMoves getMoves(ChessPieceMatrix<AbstractServerChessPiece> matrix, ChessCell cell) {
		ChessMoves moves = new ChessMoves();
		return moves;
	}
	
	@Override
	public IChessPiece copy() {
		return INSTANCE;
	}
}
