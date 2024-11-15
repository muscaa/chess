package muscaa.chess.server.board.pieces;

import muscaa.chess.server.board.AbstractServerChessPiece;
import muscaa.chess.shared.board.ChessCell;
import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.shared.board.ChessMoveType;
import muscaa.chess.shared.board.ChessMoves;
import muscaa.chess.shared.board.Validators;

public class ServerKnightChessPiece extends AbstractServerChessPiece<ServerKnightChessPiece> {
	
	public ServerKnightChessPiece(ChessColor color) {
		super(4, color, ServerKnightChessPiece::new);
	}
	
	@Override
	public void findMoves(ChessMoves<AbstractServerChessPiece> moves, ChessCell cell) {
		// left top
		moves.cell(
				ChessMoveType.TAKE,
				cell.copy().add(new ChessCell(-1, -2)),
				Validators.mainCell()
		);
		moves.cell(
				ChessMoveType.TAKE,
				cell.copy().add(new ChessCell(-2, -1)),
				Validators.mainCell()
		);
		
		// left bot
		moves.cell(
				ChessMoveType.TAKE,
				cell.copy().add(new ChessCell(-1, 2)),
				Validators.mainCell()
		);
		moves.cell(
				ChessMoveType.TAKE,
				cell.copy().add(new ChessCell(-2, 1)),
				Validators.mainCell()
		);
		
		// right bot
		moves.cell(
				ChessMoveType.TAKE,
				cell.copy().add(new ChessCell(1, 2)),
				Validators.mainCell()
		);
		moves.cell(
				ChessMoveType.TAKE,
				cell.copy().add(new ChessCell(2, 1)),
				Validators.mainCell()
		);
		
		// right top
		moves.cell(
				ChessMoveType.TAKE,
				cell.copy().add(new ChessCell(1, -2)),
				Validators.mainCell()
		);
		moves.cell(
				ChessMoveType.TAKE,
				cell.copy().add(new ChessCell(2, -1)),
				Validators.mainCell()
		);
	}
}
