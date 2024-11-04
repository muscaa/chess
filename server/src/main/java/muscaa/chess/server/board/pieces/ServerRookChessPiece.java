package muscaa.chess.server.board.pieces;

import muscaa.chess.server.board.AbstractServerChessPiece;
import muscaa.chess.shared.board.ChessCell;
import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.shared.board.ChessMoves;
import muscaa.chess.shared.board.ChessPieceMatrix;
import muscaa.chess.shared.board.IChessPiece;
import muscaa.chess.shared.board.Validators;

public class ServerRookChessPiece extends AbstractServerChessPiece {
	
	public ServerRookChessPiece(ChessColor color) {
		super(5, color);
	}
	
	@Override
	public ChessMoves getMoves(ChessPieceMatrix<AbstractServerChessPiece> matrix, ChessCell cell) {
		ChessMoves moves = new ChessMoves();
		
		// horizontal
		moves.path(Validators.OUT_OF_BOUNDS, cell, cell.copy().add(ChessCell.SIZE_ZERO), ChessCell.ONE_ZERO);
		moves.path(Validators.OUT_OF_BOUNDS, cell, cell.copy().subtract(ChessCell.SIZE_ZERO), ChessCell.ONE_ZERO.copy().negate());
		
		// vertical
		moves.path(Validators.OUT_OF_BOUNDS, cell, cell.copy().add(ChessCell.ZERO_SIZE), ChessCell.ZERO_ONE);
		moves.path(Validators.OUT_OF_BOUNDS, cell, cell.copy().subtract(ChessCell.ZERO_SIZE), ChessCell.ZERO_ONE.copy().negate());
		
		return moves;
	}
	
	@Override
	public IChessPiece copy() {
		ServerRookChessPiece copy = new ServerRookChessPiece(color);
		return copy;
	}
}
