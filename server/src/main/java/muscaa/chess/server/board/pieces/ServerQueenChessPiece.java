package muscaa.chess.server.board.pieces;

import muscaa.chess.server.board.AbstractServerChessPiece;
import muscaa.chess.shared.board.ChessCell;
import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.shared.board.ChessMoves;
import muscaa.chess.shared.board.IChessPiece;
import muscaa.chess.shared.board.Validators;

public class ServerQueenChessPiece extends AbstractServerChessPiece {
	
	public ServerQueenChessPiece(ChessColor color) {
		super(2, color);
	}
	
	@Override
	public void findMoves(ChessMoves<AbstractServerChessPiece> moves, ChessCell cell) {
		// horizontal
		moves.path(
				cell,
				cell.copy().add(ChessCell.SIZE_ZERO),
				ChessCell.ONE_ZERO,
				Validators.mainPath()
		);
		moves.path(
				cell,
				cell.copy().subtract(ChessCell.SIZE_ZERO),
				ChessCell.ONE_ZERO.copy().negate(),
				Validators.mainPath()
		);
		
		// vertical
		moves.path(
				cell,
				cell.copy().add(ChessCell.ZERO_SIZE),
				ChessCell.ZERO_ONE,
				Validators.mainPath()
		);
		moves.path(
				cell,
				cell.copy().subtract(ChessCell.ZERO_SIZE),
				ChessCell.ZERO_ONE.copy().negate(),
				Validators.mainPath()
		);
		
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
		ServerQueenChessPiece copy = new ServerQueenChessPiece(color);
		return copy;
	}
}
