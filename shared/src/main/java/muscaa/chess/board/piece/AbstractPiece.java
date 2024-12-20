package muscaa.chess.board.piece;

import java.util.Map;

import muscaa.chess.board.Cell;
import muscaa.chess.board.Team;
import muscaa.chess.board.matrix.Matrix;
import muscaa.chess.board.piece.move.AbstractMove;

public abstract class AbstractPiece implements IPiece {
	
	protected final PieceEntry<? extends AbstractPiece> registryEntry;
	protected final Team team;
	
	public int totalMoves = 0;
	
	public AbstractPiece(PieceEntry<? extends AbstractPiece> registryEntry, Team team) {
		this.registryEntry = registryEntry;
		this.team = team;
	}
	
	public abstract void findMoves(Map<Cell, AbstractMove> moves, Matrix matrix, Cell from);
	
	public void onPreMove(Matrix matrix, Cell from, Cell to) {
	}
	
	public void onPostMove(Matrix matrix, Cell from, Cell to) {
		totalMoves++;
	}
	
	public boolean isCheckable() {
		return false;
	}
	
	@Override
	public PieceEntry<?> getRegistryEntry() {
		return registryEntry;
	}
	
	@Override
	public Team getTeam() {
		return team;
	}
}
