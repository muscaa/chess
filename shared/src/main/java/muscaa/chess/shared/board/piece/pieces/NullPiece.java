package muscaa.chess.shared.board.piece.pieces;

import java.util.Map;

import muscaa.chess.shared.board.Cell;
import muscaa.chess.shared.board.Team;
import muscaa.chess.shared.board.matrix.Matrix;
import muscaa.chess.shared.board.piece.AbstractPiece;
import muscaa.chess.shared.board.piece.PieceEntry;
import muscaa.chess.shared.board.piece.move.AbstractMove;
import muscaa.chess.shared.registry.registries.PieceRegistry;
import muscaa.chess.shared.registry.registries.TeamRegistry;

public class NullPiece extends AbstractPiece {
	
	public static final NullPiece INSTANCE = new NullPiece();
	
	public NullPiece() {
		super(null, null);
	}
	
	@Override
	public void findMoves(Map<Cell, AbstractMove> moves, Matrix matrix, Cell from) {}
	
	@Override
	public PieceEntry<?> getRegistryEntry() {
		return PieceRegistry.NULL;
	}
	
	@Override
	public Team getTeam() {
		return TeamRegistry.NULL;
	}
}
