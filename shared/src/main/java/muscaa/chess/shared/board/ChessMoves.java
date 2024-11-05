package muscaa.chess.shared.board;

import java.util.LinkedList;
import java.util.List;

public class ChessMoves<P extends IChessPiece> {
	
	private final ChessPieceMatrix<P> matrix;
	private final List<ChessCell> list = new LinkedList<>();
	
	public ChessMoves(ChessPieceMatrix<P> matrix) {
		this.matrix = matrix;
	}
	
	public ValidationResult cell(ChessCell cell, IValidator... validators) {
		for (IValidator validator : validators) {
			ValidationResult result = validator.validate(cell);
			if (result != ValidationResult.ACCEPT) return result;
		}
		list.add(cell.copy());
		return ValidationResult.ACCEPT;
	}
	
	public ValidationResult path(ChessCell origin, ChessCell destination, ChessCell increment, IValidator... validators) {
		ChessCell cell = origin.copy();
		
		while (!cell.equals(destination)) {
			cell.add(increment); // skip origin
			
			ValidationResult result = cell(cell, validators);
			if (result == ValidationResult.STOP) return ValidationResult.STOP;
		}
		
		return ValidationResult.ACCEPT;
	}
	
	public ChessPieceMatrix<P> getMatrix() {
		return matrix;
	}
	
	public List<ChessCell> getList() {
		return list;
	}
}
