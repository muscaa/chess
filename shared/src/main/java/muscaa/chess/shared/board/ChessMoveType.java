package muscaa.chess.shared.board;

public enum ChessMoveType {
	UNDEFINED,
	MOVE,
	TAKE,
	;
	
	public int getID() {
		return ordinal();
	}
	
	public static ChessMoveType of(int id) {
		return id >= ChessMoveType.values().length || id < 0 ? UNDEFINED
				: ChessMoveType.values()[id];
	}
}
