package muscaa.chess.board.piece.pieces;

import java.util.List;

import muscaa.chess.board.Team;
import muscaa.chess.board.piece.AbstractPiece;
import muscaa.chess.board.piece.MultiPiece;
import muscaa.chess.board.piece.PieceEntry;
import muscaa.chess.registry.registries.PieceRegistry;

public class QueenPiece extends MultiPiece {
	
	public QueenPiece(PieceEntry<QueenPiece> regEntry, Team team) {
		super(regEntry, team);
	}
	
	@Override
	protected List<PieceEntry<? extends AbstractPiece>> getPieceEntries() {
		return List.of(PieceRegistry.ROOK, PieceRegistry.BISHOP);
	}
}
