package muscaa.chess.board.piece.move.moves;

import muscaa.chess.board.Cell;
import muscaa.chess.board.matrix.Matrix;
import muscaa.chess.board.piece.move.AbstractMove;
import muscaa.chess.utils.NamespacePath;

public class CastleMove extends AbstractMove {
	
	public CastleMove(NamespacePath id) {
		super(id);
	}
	
	@Override
	public void doMove(Matrix matrix, Cell from, Cell to) {
		// TODO
	}
}
