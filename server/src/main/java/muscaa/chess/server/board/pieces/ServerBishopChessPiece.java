package muscaa.chess.server.board.pieces;

import muscaa.chess.server.board.AbstractServerChessPiece;
import muscaa.chess.shared.board.ChessCell;
import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.shared.board.ChessMoves;
import muscaa.chess.shared.board.ChessPieceMatrix;
import muscaa.chess.shared.board.IChessPiece;
import muscaa.chess.shared.board.Validators;

public class ServerBishopChessPiece extends AbstractServerChessPiece {
	
	public ServerBishopChessPiece(ChessColor color) {
		super(3, color);
	}
	
	@Override
	public ChessMoves getMoves(ChessPieceMatrix<AbstractServerChessPiece> matrix, ChessCell cell) {
		ChessMoves moves = new ChessMoves();
		
		// primary diagonal
		moves.path(Validators.OUT_OF_BOUNDS, cell, cell.copy().add(ChessCell.SIZE_SIZE), ChessCell.ONE_ONE);
		moves.path(Validators.OUT_OF_BOUNDS, cell, cell.copy().subtract(ChessCell.SIZE_SIZE), ChessCell.ONE_ONE.copy().negate());
		
		// secondary diagonal
		moves.path(Validators.OUT_OF_BOUNDS, cell, cell.copy().add(ChessCell.SIZE_ZERO).subtract(ChessCell.ZERO_SIZE), ChessCell.ONE_ZERO.copy().subtract(ChessCell.ZERO_ONE));
		moves.path(Validators.OUT_OF_BOUNDS, cell, cell.copy().subtract(ChessCell.SIZE_ZERO).add(ChessCell.ZERO_SIZE), ChessCell.ZERO_ONE.copy().subtract(ChessCell.ONE_ZERO));
		
		return moves;
	}
	
	@Override
	public IChessPiece copy() {
		ServerBishopChessPiece copy = new ServerBishopChessPiece(color);
		return copy;
	}
}
