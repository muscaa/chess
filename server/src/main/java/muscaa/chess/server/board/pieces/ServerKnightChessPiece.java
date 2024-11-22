package muscaa.chess.server.board.pieces;

import fluff.functions.gen.Func;
import muscaa.chess.server.board.AbstractServerChessPiece;
import muscaa.chess.shared.board.ChessCell;
import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.shared.board.ChessMoveType;
import muscaa.chess.shared.board.ChessMoves;
import muscaa.chess.shared.board.IValidator;

public class ServerKnightChessPiece extends AbstractServerChessPiece<ServerKnightChessPiece> {
	
	public ServerKnightChessPiece(ChessColor color) {
		super(4, color, ServerKnightChessPiece::new);
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
		// left top
		moves.cell(
				ChessMoveType.TAKE,
				cell.copy().add(new ChessCell(-1, -2)),
				validatorFunc.invoke()
		);
		moves.cell(
				ChessMoveType.TAKE,
				cell.copy().add(new ChessCell(-2, -1)),
				validatorFunc.invoke()
		);
		
		// left bot
		moves.cell(
				ChessMoveType.TAKE,
				cell.copy().add(new ChessCell(-1, 2)),
				validatorFunc.invoke()
		);
		moves.cell(
				ChessMoveType.TAKE,
				cell.copy().add(new ChessCell(-2, 1)),
				validatorFunc.invoke()
		);
		
		// right bot
		moves.cell(
				ChessMoveType.TAKE,
				cell.copy().add(new ChessCell(1, 2)),
				validatorFunc.invoke()
		);
		moves.cell(
				ChessMoveType.TAKE,
				cell.copy().add(new ChessCell(2, 1)),
				validatorFunc.invoke()
		);
		
		// right top
		moves.cell(
				ChessMoveType.TAKE,
				cell.copy().add(new ChessCell(1, -2)),
				validatorFunc.invoke()
		);
		moves.cell(
				ChessMoveType.TAKE,
				cell.copy().add(new ChessCell(2, -1)),
				validatorFunc.invoke()
		);
	}
}
