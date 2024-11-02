package muscaa.chess.shared.board;

public enum ChessColor {
	WHITE,
	BLACK,
	;
	
	public ChessColor invert() {
		return this == WHITE ? BLACK : WHITE;
	}
}
