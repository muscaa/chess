package muscaa.chess.shared.board;

import java.util.LinkedList;
import java.util.List;

public class ChessMoves<P extends IChessPiece> {
	
	private final ChessPieceMatrix<P> matrix;
	private final P piece;
	private final boolean emulated;
	private final List<ChessMove> list = new LinkedList<>();
	
	public ChessMoves(ChessPieceMatrix<P> matrix, P piece, boolean emulated) {
		this.matrix = matrix;
		this.piece = piece;
		this.emulated = emulated;
	}
	
	public ValidationResult cell(ChessMoveType type, ChessCell cell, IValidator<P> validator) {
		ValidationResult result = validator.validate(this, cell);
		
		if (result == ValidationResult.VALID || emulated) {
			list.add(new ChessMove(cell.copy(), type));
		}
		return result;
	}
	
	public ValidationResult path(ChessMoveType type, ChessCell origin, ChessCell destination, ChessCell increment, IValidator<P> validator) {
		ChessCell cell = origin.copy();
		
		while (!cell.equals(destination)) {
			cell.add(increment); // skip origin
			
			ValidationResult result = cell(type, cell, validator);
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
	
	public boolean isEmulated() {
		return emulated;
	}
	
	public List<ChessMove> getList() {
		return list;
	}
}
