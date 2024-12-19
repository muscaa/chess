package muscaa.chess.shared.board.piece;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import muscaa.chess.shared.board.Cell;
import muscaa.chess.shared.board.Team;
import muscaa.chess.shared.board.matrix.Matrix;
import muscaa.chess.shared.board.piece.move.AbstractMove;

public abstract class MultiPiece extends AbstractPiece {
	
	protected final List<AbstractPiece> pieces = new LinkedList<>();
	
	public MultiPiece(PieceEntry<? extends AbstractPiece> regEntry, Team team) {
		super(regEntry, team);
		
		for (PieceEntry<? extends AbstractPiece> entry : getPieceEntries()) {
			pieces.add(entry.create(team));
		}
	}
	
	protected abstract List<PieceEntry<? extends AbstractPiece>> getPieceEntries();
	
	@Override
	public void findMoves(Map<Cell, AbstractMove> moves, Matrix matrix, Cell from) {
		for (AbstractPiece piece : pieces) {
			piece.findMoves(moves, matrix, from);
		}
	}
	
	@Override
	public void onPreMove(Matrix matrix, Cell from, Cell to) {
		super.onPreMove(matrix, from, to);
		
		for (AbstractPiece piece : pieces) {
			piece.onPreMove(matrix, from, to);
		}
	}
	
	@Override
	public void onPostMove(Matrix matrix, Cell from, Cell to) {
		super.onPostMove(matrix, from, to);
		
		for (AbstractPiece piece : pieces) {
			piece.onPostMove(matrix, from, to);
		}
	}
}
