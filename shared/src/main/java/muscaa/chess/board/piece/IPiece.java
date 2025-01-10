package muscaa.chess.board.piece;

import muscaa.chess.board.TeamValue;

public interface IPiece<V extends AbstractPieceValue> {
	
	V getRegistryValue();
	
	TeamValue getTeam();
}
