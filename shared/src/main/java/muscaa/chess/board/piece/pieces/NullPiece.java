package muscaa.chess.board.piece.pieces;

import java.util.Map;

import muscaa.chess.board.Cell;
import muscaa.chess.board.Team;
import muscaa.chess.board.matrix.Matrix;
import muscaa.chess.board.piece.AbstractPiece;
import muscaa.chess.board.piece.PieceEntry;
import muscaa.chess.board.piece.move.AbstractMove;
import muscaa.chess.registry.registries.PieceRegistry;
import muscaa.chess.registry.registries.TeamRegistry;

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
