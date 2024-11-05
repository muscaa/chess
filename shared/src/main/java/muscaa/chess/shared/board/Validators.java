package muscaa.chess.shared.board;

public class Validators {
	
	public static final IValidator OUT_OF_BOUNDS = (moves, cell) -> {
		if (cell.x < 0 || cell.y < 0 || cell.x >= ChessPieceMatrix.SIZE || cell.y >= ChessPieceMatrix.SIZE) {
			return ValidationResult.INVALID;
		}
		return ValidationResult.VALID;
	};
	public static final IValidator SAME_COLOR = (moves, cell) -> {
		ChessColor color = moves.getPiece().getColor();
		
		if (moves.getMatrix().get(cell).getColor() == color) {
			return ValidationResult.INVALID;
		}
		return ValidationResult.VALID;
	};
	
	public static final IValidator DEFAULT = of(OUT_OF_BOUNDS, SAME_COLOR);
	
	public static IValidator of(IValidator... validators) {
		return (moves, cell) -> {
			for (IValidator v : validators) {
				if (v.validate(moves, cell) == ValidationResult.INVALID) {
					return ValidationResult.INVALID;
				}
			}
			return ValidationResult.VALID;
		};
	}
}
