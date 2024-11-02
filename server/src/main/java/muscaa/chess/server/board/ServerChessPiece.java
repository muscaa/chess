package muscaa.chess.server.board;

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
	
	private ServerChessPiece(String id) {
		this.id = id;
	}
	
	@Override
	public String getID() {
		return id;
	}
}
