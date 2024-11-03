package muscaa.chess.shared.board;

public enum ChessColor {
	WHITE,
	BLACK,
	NONE,
	;
	
	public int getID() {
		return ordinal();
	}
	
	public ChessColor invert() {
		return switch (this) {
			case WHITE -> BLACK;
			case BLACK -> WHITE;
			default -> NONE;
		};
	}
	
	public static ChessColor of(int id) {
		return id >= ChessColor.values().length || id < 0 ? NONE
				: ChessColor.values()[id];
	}
}
