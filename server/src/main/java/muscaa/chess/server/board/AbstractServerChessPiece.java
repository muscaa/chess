package muscaa.chess.server.board;

import muscaa.chess.shared.board.ChessCell;
import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.shared.board.ChessMoves;
import muscaa.chess.shared.board.IChessPiece;

public abstract class AbstractServerChessPiece implements IChessPiece {
	
	protected final int id;
	protected final ChessColor color;
	
	// TODO int movesCount?
	
	public AbstractServerChessPiece(int id, ChessColor color) {
		this.id = color == ChessColor.WHITE ? id : -id;
		this.color = color;
	}
	
	public abstract void findMoves(ChessMoves<AbstractServerChessPiece> moves, ChessCell cell);
	
	@Override
	public int getID() {
		return id;
	}
	
	@Override
	public ChessColor getColor() {
		return color;
	}
}
