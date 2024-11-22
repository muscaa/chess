package muscaa.chess.server.board.pieces;

import fluff.functions.gen.Func;
import muscaa.chess.server.board.AbstractServerChessPiece;
import muscaa.chess.shared.board.ChessCell;
import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.shared.board.ChessMoveType;
import muscaa.chess.shared.board.ChessMoves;
import muscaa.chess.shared.board.IValidator;

public class ServerBishopChessPiece extends AbstractServerChessPiece<ServerBishopChessPiece> {
	
	public ServerBishopChessPiece(ChessColor color) {
		super(3, color, ServerBishopChessPiece::new);
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
		// primary diagonal
		moves.path(
				ChessMoveType.TAKE,
				cell,
				cell.copy().add(ChessCell.SIZE_SIZE),
				ChessCell.ONE_ONE,
				validatorFunc.invoke()
		);
		moves.path(
				ChessMoveType.TAKE,
				cell,
				cell.copy().subtract(ChessCell.SIZE_SIZE),
				ChessCell.ONE_ONE.copy().negate(),
				validatorFunc.invoke()
		);
		
		// secondary diagonal
		moves.path(
				ChessMoveType.TAKE,
				cell,
				cell.copy().add(ChessCell.SIZE_ZERO).subtract(ChessCell.ZERO_SIZE),
				ChessCell.ONE_ZERO.copy().subtract(ChessCell.ZERO_ONE),
				validatorFunc.invoke()
		);
		moves.path(
				ChessMoveType.TAKE,
				cell,
				cell.copy().subtract(ChessCell.SIZE_ZERO).add(ChessCell.ZERO_SIZE),
				ChessCell.ZERO_ONE.copy().subtract(ChessCell.ONE_ZERO),
				validatorFunc.invoke()
		);
	}
}
