package muscaa.chess.board;

import static muscaa.chess.board.ChessPieceType.*;

public class Board {
	
	public static final int SIZE = 8;
	
	public ChessPieceType[][] pieces = new ChessPieceType[][] {
		{ BLACK_ROOK, BLACK_KNIGHT, BLACK_BISHOP, BLACK_QUEEN, BLACK_KING, BLACK_BISHOP, BLACK_KNIGHT, BLACK_ROOK },
		{ BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN },
		{ null, null, null, null, null, null, null, null },
		{ null, null, null, null, null, null, null, null },
		{ null, null, null, null, null, null, null, null },
		{ null, null, null, null, null, null, null, null },
		{ WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN },
		{ WHITE_ROOK, WHITE_KNIGHT, WHITE_BISHOP, WHITE_QUEEN, WHITE_KING, WHITE_BISHOP, WHITE_KNIGHT, WHITE_ROOK },
	};
	
	public ChessPieceType getPiece(int x, int y) {
		if (getColor() == ChessColor.BLACK) {
			x = SIZE - x - 1;
			y = SIZE - y - 1;
		}
		
		return pieces[y][x];
	}
	
	public void setPiece(int x, int y, ChessPieceType pieceType) {
		if (getColor() == ChessColor.BLACK) {
			x = SIZE - x - 1;
			y = SIZE - y - 1;
		}
		
		pieces[y][x] = pieceType;
	}
	
	public ChessColor getColor() {
		return ChessColor.WHITE;
	}
	
	public void move(int x1, int y1, int x2, int y2) {
		ChessPieceType pieceType = getPiece(x1, y1);
		if (pieceType == null) return;
		
		setPiece(x2, y2, pieceType);
		setPiece(x1, y1, null);
	}
}
