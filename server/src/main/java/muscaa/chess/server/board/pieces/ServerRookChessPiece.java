package muscaa.chess.server.board.pieces;

import muscaa.chess.server.board.AbstractServerChessPiece;
import muscaa.chess.shared.board.ChessCell;
import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.shared.board.ChessMoves;
import muscaa.chess.shared.board.IChessPiece;
import muscaa.chess.shared.board.IValidator;
import muscaa.chess.shared.board.Validators;

public class ServerRookChessPiece extends AbstractServerChessPiece {
	
	public ServerRookChessPiece(ChessColor color) {
		super(5, color);
	}
	
	@Override
	public void findMoves(ChessMoves<AbstractServerChessPiece> moves, ChessCell cell) {
		IValidator main = Validators.and(
				Validators.IN_BOUNDS,
				Validators.or(
						Validators.EMPTY,
						Validators.DIFFERENT_COLOR
				)
		);
		
		// horizontal
		moves.path(
				cell,
				cell.copy().add(ChessCell.SIZE_ZERO),
				ChessCell.ONE_ZERO,
				main
		);
		moves.path(
				cell,
				cell.copy().subtract(ChessCell.SIZE_ZERO),
				ChessCell.ONE_ZERO.copy().negate(),
				main
		);
		
		// vertical
		moves.path(
				cell,
				cell.copy().add(ChessCell.ZERO_SIZE),
				ChessCell.ZERO_ONE,
				main
		);
		moves.path(
				cell,
				cell.copy().subtract(ChessCell.ZERO_SIZE),
				ChessCell.ZERO_ONE.copy().negate(),
				main
		);
	}
	
	@Override
	public IChessPiece copy() {
		ServerRookChessPiece copy = new ServerRookChessPiece(color);
		return copy;
	}
}
