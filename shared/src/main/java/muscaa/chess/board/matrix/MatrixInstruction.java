package muscaa.chess.board.matrix;

import muscaa.chess.board.Cell;
import muscaa.chess.board.piece.AbstractServerPiece;

public class MatrixInstruction {
	
	private final Cell cell;
	private final AbstractServerPiece oldPiece;
	private final AbstractServerPiece newPiece;
	
	public MatrixInstruction(Cell cell, AbstractServerPiece oldPiece, AbstractServerPiece newPiece) {
		this.cell = cell;
		this.oldPiece = oldPiece;
		this.newPiece = newPiece;
	}
	
	public Cell getCell() {
		return cell;
	}
	
	public AbstractServerPiece getOldPiece() {
		return oldPiece;
	}
	
	public AbstractServerPiece getNewPiece() {
		return newPiece;
	}
}
