package muscaa.chess.server.board.pieces;

import fluff.functions.gen.Func;
import muscaa.chess.server.board.AbstractServerChessPiece;
import muscaa.chess.shared.board.ChessCell;
import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.shared.board.ChessMoves;
import muscaa.chess.shared.board.IValidator;

public class ServerQueenChessPiece extends AbstractServerChessPiece<ServerQueenChessPiece> {
	
	public ServerQueenChessPiece(ChessColor color) {
		super(2, color, ServerQueenChessPiece::new);
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
		ServerRookChessPiece.moves(moves, cell, validatorFunc);
		ServerBishopChessPiece.moves(moves, cell, validatorFunc);
	}
}
