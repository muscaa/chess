package muscaa.chess.board.piece;

import muscaa.chess.board.Team;

public interface IPiece {
	
	PieceEntry<?> getRegistryEntry();
	
	Team getTeam();
}
