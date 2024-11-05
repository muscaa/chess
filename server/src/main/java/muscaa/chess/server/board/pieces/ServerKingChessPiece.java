package muscaa.chess.server.board.pieces;

import muscaa.chess.server.board.AbstractServerChessPiece;
import muscaa.chess.shared.board.ChessCell;
import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.shared.board.ChessMoves;
import muscaa.chess.shared.board.IChessPiece;
import muscaa.chess.shared.board.Validators;

public class ServerKingChessPiece extends AbstractServerChessPiece {
	
	public ServerKingChessPiece(ChessColor color) {
		super(1, color);
	}
	
	@Override
	public void findMoves(ChessMoves<AbstractServerChessPiece> moves, ChessCell cell) {
		// horizontal
		moves.cell(cell.copy().add(ChessCell.ONE_ZERO), Validators.DEFAULT);
		moves.cell(cell.copy().subtract(ChessCell.ONE_ZERO), Validators.DEFAULT);
		
		// vertical
		moves.cell(cell.copy().add(ChessCell.ZERO_ONE), Validators.DEFAULT);
		moves.cell(cell.copy().subtract(ChessCell.ZERO_ONE), Validators.DEFAULT);
		
		// primary diagonal
		moves.cell(cell.copy().add(ChessCell.ONE_ONE), Validators.DEFAULT);
		moves.cell(cell.copy().subtract(ChessCell.ONE_ONE), Validators.DEFAULT);
		
		// secondary diagonal
		moves.cell(cell.copy().add(ChessCell.ONE_ZERO).subtract(ChessCell.ZERO_ONE), Validators.DEFAULT);
		moves.cell(cell.copy().subtract(ChessCell.ONE_ZERO).add(ChessCell.ZERO_ONE), Validators.DEFAULT);
	}
	
	@Override
	public IChessPiece copy() {
		ServerKingChessPiece copy = new ServerKingChessPiece(color);
		return copy;
	}
}
