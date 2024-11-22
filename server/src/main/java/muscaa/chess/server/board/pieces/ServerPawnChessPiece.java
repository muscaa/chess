package muscaa.chess.server.board.pieces;

import muscaa.chess.server.board.AbstractServerChessPiece;
import muscaa.chess.shared.board.ChessCell;
import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.shared.board.ChessMoveType;
import muscaa.chess.shared.board.ChessMoves;
import muscaa.chess.shared.board.IValidator;
import muscaa.chess.shared.board.ValidationResult;
import muscaa.chess.shared.board.Validators;

public class ServerPawnChessPiece extends AbstractServerChessPiece<ServerPawnChessPiece> {
	
	public ServerPawnChessPiece(ChessColor color) {
		super(6, color, ServerPawnChessPiece::new);
	}
	
	@Override
	public void findMoves(ChessMoves<AbstractServerChessPiece> moves, ChessCell cell) {
		IValidator moveValidator = Validators.and(
				Validators.IN_BOUNDS,
				Validators.EMPTY
		);
		IValidator takeValidator = Validators.and(
				Validators.IN_BOUNDS,
				Validators.DIFFERENT_COLOR
		);
		
		moves(moves, cell, moveValidator, takeValidator);
	}
	
	@Override
	public void simulateMoves(ChessMoves<AbstractServerChessPiece> moves, ChessCell cell) {
		IValidator moveValidator = Validators.and(
				Validators.IN_BOUNDS,
				Validators.EMPTY
		);
		IValidator takeValidator = Validators.and(
				Validators.IN_BOUNDS
		);
		
		moves(moves, cell, moveValidator, takeValidator);
	}
	
	public void moves(ChessMoves<AbstractServerChessPiece> moves, ChessCell cell, IValidator moveValidator, IValidator takeValidator) {
		ValidationResult result = moves.cell(
				ChessMoveType.MOVE,
				cell.copy().add(color.getDirection()),
				moveValidator
		);
		moves.cell(
				ChessMoveType.MOVE,
				cell.copy().add(color.getDirection().copy().multiply(2)),
				Validators.and(
						moveValidator,
						Validators.when(piece -> totalMoves == 0 && result == ValidationResult.VALID)
				)
		);
		
		moves.cell(
				ChessMoveType.TAKE,
				cell.copy().add(color.getDirection()).add(new ChessCell(1, 0)),
				takeValidator
		);
		moves.cell(
				ChessMoveType.TAKE,
				cell.copy().add(color.getDirection()).add(new ChessCell(-1, 0)),
				takeValidator
		);
	}
}
