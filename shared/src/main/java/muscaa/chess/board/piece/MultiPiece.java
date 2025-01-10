package muscaa.chess.board.piece;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import muscaa.chess.board.Cell;
import muscaa.chess.board.TeamValue;
import muscaa.chess.board.matrix.Matrix;
import muscaa.chess.board.piece.move.AbstractMoveValue;

public abstract class MultiPiece extends AbstractServerPiece {
	
	protected final List<AbstractServerPiece> pieces = new LinkedList<>();
	
	public MultiPiece(ServerPieceValue registryValue, TeamValue team) {
		super(registryValue, team);
		
		for (ServerPieceValue p : getPieces()) {
			pieces.add(p.create(team));
		}
	}
	
	protected abstract List<ServerPieceValue> getPieces();
	
	@Override
	public void findMoves(Map<Cell, AbstractMoveValue> moves, Matrix matrix, Cell from) {
		for (AbstractServerPiece piece : pieces) {
			piece.findMoves(moves, matrix, from);
		}
	}
	
	@Override
	public void onPreMove(Matrix matrix, Cell from, Cell to) {
		super.onPreMove(matrix, from, to);
		
		for (AbstractServerPiece piece : pieces) {
			piece.onPreMove(matrix, from, to);
		}
	}
	
	@Override
	public void onPostMove(Matrix matrix, Cell from, Cell to) {
		super.onPostMove(matrix, from, to);
		
		for (AbstractServerPiece piece : pieces) {
			piece.onPostMove(matrix, from, to);
		}
	}
}
