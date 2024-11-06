package muscaa.chess.server.board.pieces;

import muscaa.chess.server.board.AbstractServerChessPiece;
import muscaa.chess.shared.board.ChessCell;
import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.shared.board.ChessMoves;
import muscaa.chess.shared.board.IChessPiece;
import muscaa.chess.shared.board.IValidator;
import muscaa.chess.shared.board.Validators;

public class ServerKingChessPiece extends AbstractServerChessPiece {
	
	public ServerKingChessPiece(ChessColor color) {
		super(1, color);
	}
	
	@Override
	public void findMoves(ChessMoves<AbstractServerChessPiece> moves, ChessCell cell) {
		IValidator main = Validators.and(
				Validators.IN_BOUNDS,
				Validators.or(
						Validators.EMPTY,
						Validators.DIFFERENT_COLOR
				)
				// TODO not near another king, not in check
		);
		
		// horizontal
		moves.cell(cell.copy().add(ChessCell.ONE_ZERO), main);
		moves.cell(cell.copy().subtract(ChessCell.ONE_ZERO), main);
		
		// vertical
		moves.cell(cell.copy().add(ChessCell.ZERO_ONE), main);
		moves.cell(cell.copy().subtract(ChessCell.ZERO_ONE), main);
		
		// primary diagonal
		moves.cell(cell.copy().add(ChessCell.ONE_ONE), main);
		moves.cell(cell.copy().subtract(ChessCell.ONE_ONE), main);
		
		// secondary diagonal
		moves.cell(cell.copy().add(ChessCell.ONE_ZERO).subtract(ChessCell.ZERO_ONE), main);
		moves.cell(cell.copy().subtract(ChessCell.ONE_ZERO).add(ChessCell.ZERO_ONE), main);
	}
	
	@Override
	public IChessPiece copy() {
		ServerKingChessPiece copy = new ServerKingChessPiece(color);
		return copy;
	}
}
