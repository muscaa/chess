package muscaa.chess.shared.board;

public enum ChessColor {
	WHITE(new ChessCell(0, -1)),
	BLACK(new ChessCell(0, 1)),
	NONE(new ChessCell(0, 0)),
	;
	
	private final ChessCell direction;
	
	private ChessColor(ChessCell direction) {
		this.direction = direction;
	}
	
	public ChessCell getDirection() {
		return direction;
	}
	
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
