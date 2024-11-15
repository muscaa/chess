package muscaa.chess.server.board.pieces;

import muscaa.chess.server.board.AbstractServerChessPiece;
import muscaa.chess.shared.board.ChessCell;
import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.shared.board.ChessMoveType;
import muscaa.chess.shared.board.ChessMoves;
import muscaa.chess.shared.board.IValidator;
import muscaa.chess.shared.board.Validators;

public class ServerPawnChessPiece extends AbstractServerChessPiece<ServerPawnChessPiece> {
	
	public ServerPawnChessPiece(ChessColor color) {
		super(6, color, ServerPawnChessPiece::new);
	}
	
	@Override
	public void findMoves(ChessMoves<AbstractServerChessPiece> moves, ChessCell cell) {
		IValidator main = Validators.and(
				Validators.IN_BOUNDS,
				Validators.EMPTY
		);
		IValidator take = Validators.and(
				Validators.IN_BOUNDS,
				Validators.DIFFERENT_COLOR
		);
		
		moves.cell(
				ChessMoveType.MOVE,
				cell.copy().add(color.getDirection()),
				main
		);
		moves.cell(
				ChessMoveType.MOVE,
				cell.copy().add(color.getDirection().copy().multiply(2)),
				Validators.and(
						main,
						Validators.when(piece -> totalMoves == 0)
				)
		);
		
		moves.cell(
				ChessMoveType.TAKE,
				cell.copy().add(color.getDirection()).add(new ChessCell(1, 0)),
				take
		);
		moves.cell(
				ChessMoveType.TAKE,
				cell.copy().add(color.getDirection()).add(new ChessCell(-1, 0)),
				take
		);
	}
}
