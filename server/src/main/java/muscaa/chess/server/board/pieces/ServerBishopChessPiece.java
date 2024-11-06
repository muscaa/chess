package muscaa.chess.server.board.pieces;

import muscaa.chess.server.board.AbstractServerChessPiece;
import muscaa.chess.shared.board.ChessCell;
import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.shared.board.ChessMoves;
import muscaa.chess.shared.board.IChessPiece;
import muscaa.chess.shared.board.Validators;

public class ServerBishopChessPiece extends AbstractServerChessPiece {
	
	public ServerBishopChessPiece(ChessColor color) {
		super(3, color);
	}
	
	@Override
	public void findMoves(ChessMoves<AbstractServerChessPiece> moves, ChessCell cell) {
		// primary diagonal
		moves.path(
				cell,
				cell.copy().add(ChessCell.SIZE_SIZE),
				ChessCell.ONE_ONE,
				Validators.mainPath()
		);
		moves.path(
				cell,
				cell.copy().subtract(ChessCell.SIZE_SIZE),
				ChessCell.ONE_ONE.copy().negate(),
				Validators.mainPath()
		);
		
		// secondary diagonal
		moves.path(
				cell,
				cell.copy().add(ChessCell.SIZE_ZERO).subtract(ChessCell.ZERO_SIZE),
				ChessCell.ONE_ZERO.copy().subtract(ChessCell.ZERO_ONE),
				Validators.mainPath()
		);
		moves.path(
				cell,
				cell.copy().subtract(ChessCell.SIZE_ZERO).add(ChessCell.ZERO_SIZE),
				ChessCell.ZERO_ONE.copy().subtract(ChessCell.ONE_ZERO),
				Validators.mainPath()
		);
	}
	
	@Override
	public IChessPiece copy() {
		ServerBishopChessPiece copy = new ServerBishopChessPiece(color);
		return copy;
	}
}
