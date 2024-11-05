package muscaa.chess.shared.board;

import java.util.LinkedList;
import java.util.List;

public class ChessMoves<P extends IChessPiece> {
	
	private final ChessPieceMatrix<P> matrix;
	private final P piece;
	private final List<ChessCell> list = new LinkedList<>();
	
	public ChessMoves(ChessPieceMatrix<P> matrix, P piece) {
		this.matrix = matrix;
		this.piece = piece;
	}
	
	public ValidationResult cell(ChessCell cell, IValidator<P> validator) {
		ValidationResult result = validator.validate(this, cell);
		
		if (result == ValidationResult.VALID) {
			list.add(cell.copy());
		}
		return result;
	}
	
	public ValidationResult path(ChessCell origin, ChessCell destination, ChessCell increment, IValidator<P> validator) {
		ChessCell cell = origin.copy();
		
		while (!cell.equals(destination)) {
			cell.add(increment); // skip origin
			
			ValidationResult result = cell(cell, validator);
			if (result == ValidationResult.INVALID) return result;
		}
		
		return ValidationResult.VALID;
	}
	
	public ChessPieceMatrix<P> getMatrix() {
		return matrix;
	}
	
	public P getPiece() {
		return piece;
	}
	
	public List<ChessCell> getList() {
		return list;
	}
}
