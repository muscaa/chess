package muscaa.chess.server.board.pieces;

import fluff.functions.gen.Func;
import muscaa.chess.server.board.AbstractServerChessPiece;
import muscaa.chess.shared.board.ChessCell;
import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.shared.board.ChessMoveType;
import muscaa.chess.shared.board.ChessMoves;
import muscaa.chess.shared.board.IValidator;

public class ServerRookChessPiece extends AbstractServerChessPiece<ServerRookChessPiece> {
	
	public ServerRookChessPiece(ChessColor color) {
		super(5, color, ServerRookChessPiece::new);
	}
	
	@Override
	public void findMoves(ChessMoves<AbstractServerChessPiece> moves, ChessCell cell) {
		moves(moves, cell, AbstractServerChessPiece::findValidator);
	}
	
	@Override
	public void simulateMoves(ChessMoves<AbstractServerChessPiece> moves, ChessCell cell) {
		moves(moves, cell, AbstractServerChessPiece::simulateValidator);
	}
	
	public static void moves(ChessMoves<AbstractServerChessPiece> moves, ChessCell cell, Func<IValidator> validatorFunc) {
		// horizontal
		moves.path(
				ChessMoveType.TAKE,
				cell,
				cell.copy().add(ChessCell.SIZE_ZERO),
				ChessCell.ONE_ZERO,
				validatorFunc.invoke()
		);
		moves.path(
				ChessMoveType.TAKE,
				cell,
				cell.copy().subtract(ChessCell.SIZE_ZERO),
				ChessCell.ONE_ZERO.copy().negate(),
				validatorFunc.invoke()
		);
		
		// vertical
		moves.path(
				ChessMoveType.TAKE,
				cell,
				cell.copy().add(ChessCell.ZERO_SIZE),
				ChessCell.ZERO_ONE,
				validatorFunc.invoke()
		);
		moves.path(
				ChessMoveType.TAKE,
				cell,
				cell.copy().subtract(ChessCell.ZERO_SIZE),
				ChessCell.ZERO_ONE.copy().negate(),
				validatorFunc.invoke()
		);
	}
}
