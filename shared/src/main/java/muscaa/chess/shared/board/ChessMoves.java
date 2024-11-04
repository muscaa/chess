package muscaa.chess.shared.board;

import java.util.LinkedList;
import java.util.List;

public class ChessMoves {
	
	private final List<ChessCell> list = new LinkedList<>();
	
	public ValidationResult cell(IValidator validator, ChessCell cell) {
		ValidationResult result = validator.validate(cell);
		if (result != ValidationResult.ACCEPT) return result;
		
		list.add(cell.copy());
		return ValidationResult.ACCEPT;
	}
	
	public ValidationResult path(IValidator validator, ChessCell origin, ChessCell destination, ChessCell increment) {
		ChessCell cell = origin.copy();
		
		while (!cell.equals(destination)) {
			cell.add(increment); // skip origin
			
			ValidationResult result = cell(validator, cell);
			if (result == ValidationResult.STOP) return ValidationResult.STOP;
		}
		
		return ValidationResult.ACCEPT;
	}
	
	public List<ChessCell> getList() {
		return list;
	}
}
