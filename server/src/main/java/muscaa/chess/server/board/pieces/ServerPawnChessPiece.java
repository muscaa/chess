package muscaa.chess.server.board.pieces;

import muscaa.chess.server.board.AbstractServerChessPiece;
import muscaa.chess.shared.board.ChessCell;
import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.shared.board.ChessMoves;
import muscaa.chess.shared.board.IChessPiece;
import muscaa.chess.shared.board.IValidator;
import muscaa.chess.shared.board.Validators;

public class ServerPawnChessPiece extends AbstractServerChessPiece {
	
	private int totalMoves = 0;
	
	public ServerPawnChessPiece(ChessColor color) {
		super(6, color);
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
				cell.copy().add(color.getDirection()),
				main
		);
		if (totalMoves == 0) {
			moves.cell(
					cell.copy().add(color.getDirection().copy().multiply(2)),
					main
			);
		}
		
		moves.cell(
				cell.copy().add(color.getDirection()).add(new ChessCell(1, 0)),
				take
		);
		moves.cell(
				cell.copy().add(color.getDirection()).add(new ChessCell(-1, 0)),
				take
		);
	}
	
	@Override
	protected void onMove(ChessCell cell) {
		super.onMove(cell);
		
		totalMoves++;
	}
	
	@Override
	public IChessPiece copy() {
		ServerPawnChessPiece copy = new ServerPawnChessPiece(color);
		copy.totalMoves = totalMoves;
		return copy;
	}
}
