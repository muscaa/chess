package muscaa.chess.board.piece.move.moves;

import muscaa.chess.board.Cell;
import muscaa.chess.board.matrix.Matrix;
import muscaa.chess.board.piece.AbstractPiece;
import muscaa.chess.board.piece.move.AbstractMove;
import muscaa.chess.board.piece.pieces.NullPiece;
import muscaa.chess.utils.NamespacePath;

public class BasicMove extends AbstractMove {
	
	public BasicMove(NamespacePath id) {
		super(id);
	}
	
	@Override
	public void doMove(Matrix matrix, Cell from, Cell to) {
		AbstractPiece fromPiece = matrix.get(from);
		
		matrix.set(to, fromPiece);
		matrix.set(from, NullPiece.INSTANCE);
	}
}
