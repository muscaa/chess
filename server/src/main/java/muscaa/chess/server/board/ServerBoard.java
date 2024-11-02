package muscaa.chess.server.board;

import static muscaa.chess.server.board.ServerChessPiece.*;

import fluff.vecmath.gen._int.vector.Vec2i;
import muscaa.chess.shared.board.IBoard;

public class ServerBoard implements IBoard<ServerChessPiece> {
	
	private final ServerChessPiece[][] pieces;
	
	public ServerBoard() {
		this.pieces = new ServerChessPiece[][] {
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
	
	@Override
	public void click(Vec2i cell) {
	}
	
	@Override
	public ServerChessPiece getPiece(Vec2i cell) {
		return pieces[cell.y][cell.x];
	}
	
	public ServerChessPiece[][] getPieces() {
		return pieces;
	}
}
