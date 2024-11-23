package muscaa.chess.server.board;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import muscaa.chess.shared.board.ChessCell;
import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.shared.board.ChessMove;
import muscaa.chess.shared.board.ChessMoves;
import muscaa.chess.shared.board.ChessPieceMatrix;

public class BoardUtils {
	
	public static List<ChessMove> findMoves(ChessPieceMatrix<AbstractServerChessPiece> matrix, ChessCell cell) {
		if (cell.equals(ChessCell.INVALID)) return List.of();
		
		AbstractServerChessPiece piece = matrix.get(cell);
		ChessMoves<AbstractServerChessPiece> moves = new ChessMoves<>(matrix, piece);
		piece.findMoves(moves, cell);
		return moves.getList();
	}
	
	public static Set<ChessMove> simulateMoves(ChessPieceMatrix<AbstractServerChessPiece> matrix, ChessColor color) {
		Set<ChessMove> total = new HashSet<>();
		
		for (ChessCell cell : matrix) {
			AbstractServerChessPiece piece = matrix.get(cell);
			if (piece.getColor() != color) continue;
			
			ChessMoves<AbstractServerChessPiece> moves = new ChessMoves<>(matrix, piece);
			piece.simulateMoves(moves, cell);
			
			total.addAll(moves.getList());
		}
		
		return total;
	}
}
