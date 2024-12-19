package muscaa.chess.shared.board.matrix;

import muscaa.chess.shared.board.Cell;
import muscaa.chess.shared.board.piece.AbstractPiece;

public class MatrixInstruction {
	
	private final Cell cell;
	private final AbstractPiece oldPiece;
	private final AbstractPiece newPiece;
	
	public MatrixInstruction(Cell cell, AbstractPiece oldPiece, AbstractPiece newPiece) {
		this.cell = cell;
		this.oldPiece = oldPiece;
		this.newPiece = newPiece;
	}
	
	public Cell getCell() {
		return cell;
	}
	
	public AbstractPiece getOldPiece() {
		return oldPiece;
	}
	
	public AbstractPiece getNewPiece() {
		return newPiece;
	}
}
