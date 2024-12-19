package muscaa.chess.shared.board.piece;

import muscaa.chess.shared.board.Team;

public interface IPiece {
	
	PieceEntry<?> getRegistryEntry();
	
	Team getTeam();
}
