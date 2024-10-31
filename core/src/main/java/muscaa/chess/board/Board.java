package muscaa.chess.board;

import static muscaa.chess.board.BoardPieceType.*;

public class Board {
	
	public BoardPieceType[][] getPieces() {
		return new BoardPieceType[][] {
			{ BLACK_ROOK, BLACK_KNIGHT, BLACK_BISHOP, BLACK_QUEEN, BLACK_KING, BLACK_BISHOP, BLACK_KNIGHT, BLACK_ROOK },
			{ BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN },
			{ null, null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null, null },
			{ WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN },
			{ WHITE_ROOK, WHITE_KNIGHT, WHITE_BISHOP, WHITE_QUEEN, WHITE_KING, WHITE_BISHOP, WHITE_KNIGHT, WHITE_ROOK },
		};
	}
}
