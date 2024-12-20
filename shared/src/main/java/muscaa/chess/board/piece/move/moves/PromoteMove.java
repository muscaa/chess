package muscaa.chess.board.piece.move.moves;

import muscaa.chess.board.Cell;
import muscaa.chess.board.matrix.Matrix;
import muscaa.chess.board.piece.AbstractPiece;
import muscaa.chess.board.piece.pieces.NullPiece;
import muscaa.chess.registry.registries.PieceRegistry;
import muscaa.chess.utils.NamespacePath;

public class PromoteMove extends CaptureMove {
	
	public PromoteMove(NamespacePath id) {
		super(id);
	}
	
	@Override
	public void doMove(Matrix matrix, Cell from, Cell to) {
		AbstractPiece fromPiece = matrix.get(from);
		
		matrix.set(to, PieceRegistry.QUEEN.create(fromPiece.getTeam()));
		matrix.set(from, NullPiece.INSTANCE);
	}
}
