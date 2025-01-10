package muscaa.chess.board.matrix;

import java.util.Iterator;
import java.util.LinkedList;

import muscaa.chess.board.Cell;
import muscaa.chess.board.piece.AbstractServerPiece;

public class Matrix extends AbstractMatrix<AbstractServerPiece> {
	
	protected final LinkedList<MatrixHistoryEntry> history = new LinkedList<>();
	protected MatrixHistoryEntry historyEntry;
	
	public Matrix(int width, int height) {
		super(width, height);
	}
	
	@Override
	public void set(Cell cell, AbstractServerPiece piece) {
		if (historyEntry == null) throw new MatrixException("No matrix modification in progress!");
		
		historyEntry.onSet(cell, (AbstractServerPiece) pieces[cell.y][cell.x], piece);
		pieces[cell.y][cell.x] = piece;
	}
	
	public void undo(int moves) {
		undoTo(history.size() - moves - 1);
	}
	
	public void undoTo(int moveIndex) {
		if (moveIndex < -1 || moveIndex >= history.size()) throw new MatrixException("Invalid undo move index!");
		
		for (int i = history.size() - 1; i > moveIndex; i--) {
			MatrixHistoryEntry entry = history.removeLast();
			
			Iterator<MatrixInstruction> it = entry.instructions.descendingIterator();
			while (it.hasNext()) {
				MatrixInstruction instruction = it.next();
				
				pieces[instruction.getCell().y][instruction.getCell().x] = instruction.getOldPiece();
			}
		}
	}
	
	public void begin() {
		if (historyEntry != null) throw new MatrixException("Another matrix modification in progress!");
		
		historyEntry = new MatrixHistoryEntry();
	}
	
	public void end() {
		if (historyEntry == null) throw new MatrixException("No matrix modification in progress!");
		
		history.add(historyEntry);
		historyEntry = null;
	}
	
	public int getHistorySize() {
		return history.size();
	}
}
