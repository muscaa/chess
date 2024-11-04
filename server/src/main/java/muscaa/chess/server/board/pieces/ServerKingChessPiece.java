package muscaa.chess.server.board.pieces;

import muscaa.chess.server.board.AbstractServerChessPiece;
import muscaa.chess.shared.board.ChessCell;
import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.shared.board.ChessMoves;
import muscaa.chess.shared.board.ChessPieceMatrix;
import muscaa.chess.shared.board.IChessPiece;
import muscaa.chess.shared.board.Validators;

public class ServerKingChessPiece extends AbstractServerChessPiece {
	
	public ServerKingChessPiece(ChessColor color) {
		super(1, color);
	}
	
	@Override
	public ChessMoves getMoves(ChessPieceMatrix<AbstractServerChessPiece> matrix, ChessCell cell) {
		ChessMoves moves = new ChessMoves();
		
		// horizontal
		moves.cell(Validators.OUT_OF_BOUNDS, cell.copy().add(ChessCell.ONE_ZERO));
		moves.cell(Validators.OUT_OF_BOUNDS, cell.copy().subtract(ChessCell.ONE_ZERO));
		
		// vertical
		moves.cell(Validators.OUT_OF_BOUNDS, cell.copy().add(ChessCell.ZERO_ONE));
		moves.cell(Validators.OUT_OF_BOUNDS, cell.copy().subtract(ChessCell.ZERO_ONE));
		
		// primary diagonal
		moves.cell(Validators.OUT_OF_BOUNDS, cell.copy().add(ChessCell.ONE_ONE));
		moves.cell(Validators.OUT_OF_BOUNDS, cell.copy().subtract(ChessCell.ONE_ONE));
		
		// secondary diagonal
		moves.cell(Validators.OUT_OF_BOUNDS, cell.copy().add(ChessCell.ONE_ZERO).subtract(ChessCell.ZERO_ONE));
		moves.cell(Validators.OUT_OF_BOUNDS, cell.copy().subtract(ChessCell.ONE_ZERO).add(ChessCell.ZERO_ONE));
		
		return moves;
	}
	
	@Override
	public IChessPiece copy() {
		ServerKingChessPiece copy = new ServerKingChessPiece(color);
		return copy;
	}
}
