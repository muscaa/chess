package muscaa.chess.server.board.pieces;

import muscaa.chess.server.board.AbstractServerChessPiece;
import muscaa.chess.shared.board.ChessCell;
import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.shared.board.ChessMoves;
import muscaa.chess.shared.board.IChessPiece;
import muscaa.chess.shared.board.IValidator;
import muscaa.chess.shared.board.Validators;

public class ServerKnightChessPiece extends AbstractServerChessPiece {
	
	public ServerKnightChessPiece(ChessColor color) {
		super(4, color);
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
		
		// left top
		moves.cell(cell.copy().add(new ChessCell(-1, -2)), main);
		moves.cell(cell.copy().add(new ChessCell(-2, -1)), main);
		
		// left bot
		moves.cell(cell.copy().add(new ChessCell(-1, 2)), main);
		moves.cell(cell.copy().add(new ChessCell(-2, 1)), main);
		
		// right bot
		moves.cell(cell.copy().add(new ChessCell(1, 2)), main);
		moves.cell(cell.copy().add(new ChessCell(2, 1)), main);
		
		// right top
		moves.cell(cell.copy().add(new ChessCell(1, -2)), main);
		moves.cell(cell.copy().add(new ChessCell(2, -1)), main);
	}
	
	@Override
	public IChessPiece copy() {
		ServerKnightChessPiece copy = new ServerKnightChessPiece(color);
		return copy;
	}
}
