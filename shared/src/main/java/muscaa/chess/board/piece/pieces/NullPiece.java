package muscaa.chess.board.piece.pieces;

import java.util.Map;

import muscaa.chess.board.Cell;
import muscaa.chess.board.TeamRegistry;
import muscaa.chess.board.TeamValue;
import muscaa.chess.board.matrix.ServerMatrix;
import muscaa.chess.board.piece.AbstractServerPiece;
import muscaa.chess.board.piece.ServerPieceRegistry;
import muscaa.chess.board.piece.ServerPieceValue;
import muscaa.chess.board.piece.move.AbstractMoveValue;

public class NullPiece extends AbstractServerPiece {
	
	public static final NullPiece INSTANCE = new NullPiece();
	
	public NullPiece() {
		super(null, null);
	}
	
	@Override
	public void findMoves(Map<Cell, AbstractMoveValue> moves, ServerMatrix matrix, Cell from) {}
	
	@Override
	public ServerPieceValue getRegistryValue() {
		return ServerPieceRegistry.NULL.get();
	}
	
	@Override
	public TeamValue getTeam() {
		return TeamRegistry.NULL.get();
	}
}
