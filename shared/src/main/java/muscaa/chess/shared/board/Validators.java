package muscaa.chess.shared.board;

public class Validators {
	
	public static final IValidator ACCEPT_EVERYTHING = (cell) -> ValidationResult.ACCEPT;
	public static final IValidator OUT_OF_BOUNDS = (cell) -> {
		if (cell.x < 0 || cell.y < 0 || cell.x >= ChessPieceMatrix.SIZE || cell.y >= ChessPieceMatrix.SIZE) {
			return ValidationResult.STOP;
		}
		return ValidationResult.ACCEPT;
	};
}
