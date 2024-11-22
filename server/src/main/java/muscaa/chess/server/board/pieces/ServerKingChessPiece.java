package muscaa.chess.server.board.pieces;

import java.util.HashSet;
import java.util.Set;

import fluff.functions.gen.Func;
import muscaa.chess.server.board.AbstractServerChessPiece;
import muscaa.chess.shared.board.ChessCell;
import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.shared.board.ChessMove;
import muscaa.chess.shared.board.ChessMoveType;
import muscaa.chess.shared.board.ChessMoves;
import muscaa.chess.shared.board.ChessPieceMatrix;
import muscaa.chess.shared.board.IValidator;
import muscaa.chess.shared.board.Validators;

public class ServerKingChessPiece extends AbstractServerChessPiece<ServerKingChessPiece> {
	
	public ServerKingChessPiece(ChessColor color) {
		super(1, color, ServerKingChessPiece::new);
	}
	
	@Override
	public void findMoves(ChessMoves<AbstractServerChessPiece> moves, ChessCell cell) {
		Set<ChessCell> inCheck = new HashSet<>();
		ChessPieceMatrix<AbstractServerChessPiece> matrix = moves.getMatrix();
		
		for (ChessCell ecell : matrix) {
			AbstractServerChessPiece epiece = matrix.get(ecell);
			if (epiece.getColor() == color) continue;
			
			ChessMoves<AbstractServerChessPiece> emoves = new ChessMoves<>(matrix, epiece);
			epiece.simulateMoves(emoves, ecell);
			
			emoves.getList()
					.stream()
					.filter(move -> move.getType() == ChessMoveType.TAKE || epiece instanceof ServerKingChessPiece)
					.map(ChessMove::getCell)
					.forEach(inCheck::add);
		}
		
		moves(moves, cell, () -> Validators.and(
				AbstractServerChessPiece.findValidator(),
				Validators.avoid(inCheck)
		));
	}
	
	@Override
	public void simulateMoves(ChessMoves<AbstractServerChessPiece> moves, ChessCell cell) {
		moves(moves, cell, AbstractServerChessPiece::simulateValidator);
	}
	
	public void moves(ChessMoves<AbstractServerChessPiece> moves, ChessCell cell, Func<IValidator> validatorFunc) {
		// horizontal
		moves.cell(
				ChessMoveType.MOVE,
				cell.copy().add(ChessCell.ONE_ZERO),
				validatorFunc.invoke()
		);
		moves.cell(
				ChessMoveType.MOVE,
				cell.copy().subtract(ChessCell.ONE_ZERO),
				validatorFunc.invoke()
		);
		
		// vertical
		moves.cell(
				ChessMoveType.MOVE,
				cell.copy().add(ChessCell.ZERO_ONE),
				validatorFunc.invoke()
		);
		moves.cell(
				ChessMoveType.MOVE,
				cell.copy().subtract(ChessCell.ZERO_ONE),
				validatorFunc.invoke()
		);
		
		// primary diagonal
		moves.cell(
				ChessMoveType.MOVE,
				cell.copy().add(ChessCell.ONE_ONE),
				validatorFunc.invoke()
		);
		moves.cell(
				ChessMoveType.MOVE,
				cell.copy().subtract(ChessCell.ONE_ONE),
				validatorFunc.invoke()
		);
		
		// secondary diagonal
		moves.cell(
				ChessMoveType.MOVE,
				cell.copy().add(ChessCell.ONE_ZERO).subtract(ChessCell.ZERO_ONE),
				validatorFunc.invoke()
		);
		moves.cell(
				ChessMoveType.MOVE,
				cell.copy().subtract(ChessCell.ONE_ZERO).add(ChessCell.ZERO_ONE),
				validatorFunc.invoke()
		);
	}
}
