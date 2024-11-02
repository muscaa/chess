package muscaa.chess.server.board;

import muscaa.chess.shared.board.IChessPiece;

public enum ServerChessPiece implements IChessPiece {
	WHITE_KING(),
	WHITE_QUEEN(),
	WHITE_BISHOP(),
	WHITE_KNIGHT(),
	WHITE_ROOK(),
	WHITE_PAWN(),
	
	BLACK_KING(),
	BLACK_QUEEN(),
	BLACK_BISHOP(),
	BLACK_KNIGHT(),
	BLACK_ROOK(),
	BLACK_PAWN(),
	;
}
