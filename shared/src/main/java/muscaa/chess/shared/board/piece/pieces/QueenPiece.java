package muscaa.chess.shared.board.piece.pieces;

import java.util.List;

import muscaa.chess.shared.board.Team;
import muscaa.chess.shared.board.piece.AbstractPiece;
import muscaa.chess.shared.board.piece.MultiPiece;
import muscaa.chess.shared.board.piece.PieceEntry;
import muscaa.chess.shared.registry.registries.PieceRegistry;

public class QueenPiece extends MultiPiece {
	
	public QueenPiece(PieceEntry<QueenPiece> regEntry, Team team) {
		super(regEntry, team);
	}
	
	@Override
	protected List<PieceEntry<? extends AbstractPiece>> getPieceEntries() {
		return List.of(PieceRegistry.ROOK, PieceRegistry.BISHOP);
	}
}
