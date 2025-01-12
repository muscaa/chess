package muscaa.chess.board.piece;

import java.util.Map;

import muscaa.chess.board.Cell;
import muscaa.chess.board.TeamValue;
import muscaa.chess.board.matrix.ServerMatrix;
import muscaa.chess.board.piece.move.AbstractMoveValue;

public abstract class AbstractServerPiece implements IPiece<ServerPieceValue> {
	
	protected final ServerPieceValue registryValue;
	protected final TeamValue team;
	
	public int totalMoves = 0;
	
	public AbstractServerPiece(ServerPieceValue registryValue, TeamValue team) {
		this.registryValue = registryValue;
		this.team = team;
	}
	
	public abstract void findMoves(Map<Cell, AbstractMoveValue> moves, ServerMatrix matrix, Cell from);
	
	public void onPreMove(ServerMatrix matrix, Cell from, Cell to) {
	}
	
	public void onPostMove(ServerMatrix matrix, Cell from, Cell to) {
		totalMoves++;
	}
	
	public boolean isCheckable() {
		return false;
	}
	
	@Override
	public ServerPieceValue getRegistryValue() {
		return registryValue;
	}
	
	@Override
	public TeamValue getTeam() {
		return team;
	}
}
