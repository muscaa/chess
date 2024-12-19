package muscaa.chess.shared.board.piece.move.moves;

import muscaa.chess.shared.board.Cell;
import muscaa.chess.shared.board.matrix.Matrix;
import muscaa.chess.shared.board.piece.move.AbstractMove;
import muscaa.chess.shared.utils.NamespacePath;

public class CastleMove extends AbstractMove {
	
	public CastleMove(NamespacePath id) {
		super(id);
	}
	
	@Override
	public void doMove(Matrix matrix, Cell from, Cell to) {
		// TODO
	}
}
