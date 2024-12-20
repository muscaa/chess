package muscaa.chess.board.matrix;

import java.util.LinkedList;

import muscaa.chess.board.Cell;
import muscaa.chess.board.piece.AbstractPiece;

public class MatrixHistoryEntry {
	
	public final LinkedList<MatrixInstruction> instructions = new LinkedList<>();
	
	public void onSet(Cell cell, AbstractPiece oldPiece, AbstractPiece newPiece) {
		instructions.add(new MatrixInstruction(cell, oldPiece, newPiece));
	}
}
