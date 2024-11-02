package muscaa.chess.server.board;

import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.shared.board.IChessPiece;

public enum ServerChessPiece implements IChessPiece {
	WHITE_KING("wk"),
	WHITE_QUEEN("wq"),
	WHITE_BISHOP("wb"),
	WHITE_KNIGHT("wn"),
	WHITE_ROOK("wr"),
	WHITE_PAWN("wp"),
	
	BLACK_KING("bk"),
	BLACK_QUEEN("bq"),
	BLACK_BISHOP("bb"),
	BLACK_KNIGHT("bn"),
	BLACK_ROOK("br"),
	BLACK_PAWN("bp"),
	;
	
	private final String id;
	private final ChessColor color;
	
	private ServerChessPiece(String id) {
		this.id = id;
		this.color = id.startsWith("w") ? ChessColor.WHITE : ChessColor.BLACK;
	}
	
	@Override
	public String getID() {
		return id;
	}
	
	 @Override
	public ChessColor getColor() {
		return color;
	}
}
