package muscaa.chess.server.board.pieces;

import muscaa.chess.server.board.AbstractServerChessPiece;
import muscaa.chess.shared.board.ChessCell;
import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.shared.board.ChessMoves;
import muscaa.chess.shared.board.IChessPiece;
import muscaa.chess.shared.board.Validators;

public class ServerKnightChessPiece extends AbstractServerChessPiece {
	
	public ServerKnightChessPiece(ChessColor color) {
		super(4, color);
	}
	
	@Override
	public void findMoves(ChessMoves<AbstractServerChessPiece> moves, ChessCell cell) {
		// left top
		moves.cell(
				cell.copy().add(new ChessCell(-1, -2)),
				Validators.mainCell()
		);
		moves.cell(
				cell.copy().add(new ChessCell(-2, -1)),
				Validators.mainCell()
		);
		
		// left bot
		moves.cell(
				cell.copy().add(new ChessCell(-1, 2)),
				Validators.mainCell()
		);
		moves.cell(
				cell.copy().add(new ChessCell(-2, 1)),
				Validators.mainCell()
		);
		
		// right bot
		moves.cell(
				cell.copy().add(new ChessCell(1, 2)),
				Validators.mainCell()
		);
		moves.cell(
				cell.copy().add(new ChessCell(2, 1)),
				Validators.mainCell()
		);
		
		// right top
		moves.cell(
				cell.copy().add(new ChessCell(1, -2)),
				Validators.mainCell()
		);
		moves.cell(
				cell.copy().add(new ChessCell(2, -1)),
				Validators.mainCell()
		);
	}
	
	@Override
	public IChessPiece copy() {
		ServerKnightChessPiece copy = new ServerKnightChessPiece(color);
		return copy;
	}
}
