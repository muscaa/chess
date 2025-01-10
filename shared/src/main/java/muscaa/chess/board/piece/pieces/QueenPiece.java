package muscaa.chess.board.piece.pieces;

import java.util.List;

import muscaa.chess.board.TeamValue;
import muscaa.chess.board.piece.MultiPiece;
import muscaa.chess.board.piece.ServerPieceRegistry;
import muscaa.chess.board.piece.ServerPieceValue;

public class QueenPiece extends MultiPiece {
	
	public QueenPiece(TeamValue team) {
		super(ServerPieceRegistry.QUEEN.get(), team);
	}
	
	@Override
	protected List<ServerPieceValue> getPieces() {
		return List.of(ServerPieceRegistry.ROOK.get(), ServerPieceRegistry.BISHOP.get());
	}
}
