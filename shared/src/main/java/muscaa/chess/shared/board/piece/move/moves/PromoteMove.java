package muscaa.chess.shared.board.piece.move.moves;

import muscaa.chess.shared.board.Cell;
import muscaa.chess.shared.board.matrix.Matrix;
import muscaa.chess.shared.board.piece.AbstractPiece;
import muscaa.chess.shared.board.piece.pieces.NullPiece;
import muscaa.chess.shared.registry.registries.PieceRegistry;
import muscaa.chess.shared.utils.NamespacePath;

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
