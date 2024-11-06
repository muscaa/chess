package muscaa.chess.shared.board;

import java.util.Map;

public class Validators {
	
	public static final IValidator IN_BOUNDS = (moves, cell) -> {
		if (cell.x < 0 || cell.y < 0 || cell.x >= ChessPieceMatrix.SIZE || cell.y >= ChessPieceMatrix.SIZE) {
			return ValidationResult.INVALID;
		}
		return ValidationResult.VALID;
	};
	
	public static final IValidator EMPTY = (moves, cell) -> {
		if (moves.getMatrix().get(cell).getColor() == ChessColor.NONE) {
			return ValidationResult.VALID;
		}
		return ValidationResult.INVALID;
	};
	public static final IValidator NOT_EMPTY = negate(EMPTY);
	
	public static final IValidator SAME_COLOR = (moves, cell) -> {
		ChessColor color = moves.getPiece().getColor();
		
		if (moves.getMatrix().get(cell).getColor() == color) {
			return ValidationResult.VALID;
		}
		return ValidationResult.INVALID;
	};
	public static final IValidator DIFFERENT_COLOR = and(negate(SAME_COLOR), NOT_EMPTY);
	
	public static IValidator negate(IValidator validator) {
		return (moves, cell) -> {
			if (validator.validate(moves, cell) == ValidationResult.INVALID) {
				return ValidationResult.VALID;
			}
			return ValidationResult.INVALID;
		};
	}
	
	public static IValidator and(IValidator... validators) {
		return (moves, cell) -> {
			for (IValidator v : validators) {
				if (v.validate(moves, cell) == ValidationResult.INVALID) {
					return ValidationResult.INVALID;
				}
			}
			return ValidationResult.VALID;
		};
	}
	
	public static IValidator or(IValidator... validators) {
		return (moves, cell) -> {
			for (IValidator v : validators) {
				if (v.validate(moves, cell) == ValidationResult.VALID) {
					return ValidationResult.VALID;
				}
			}
			return ValidationResult.INVALID;
		};
	}
	
	public static IValidator count(int max, Map<IValidator, Integer> validators) {
		return new IValidator<>() {
			
			private int count = 0;
			
			@Override
			public ValidationResult validate(ChessMoves<IChessPiece> moves, ChessCell cell) {
				if (count >= max) return ValidationResult.INVALID;
				
				ValidationResult result = ValidationResult.INVALID;
				for (Map.Entry<IValidator, Integer> e : validators.entrySet()) {
					result = e.getKey().validate(moves, cell);
					if (result == ValidationResult.VALID) {
						count += e.getValue();
						break;
					}
				}
				return result;
			}
		};
	}
	
	public static IValidator mainPath() {
		return and(
				IN_BOUNDS,
				count(1, Map.of(
						EMPTY, 0,
						DIFFERENT_COLOR, 1
				))
		);
	}
	
	public static IValidator mainCell() {
		return and(
				IN_BOUNDS,
				or(
						Validators.EMPTY,
						Validators.DIFFERENT_COLOR
				)
		);
	}
}
