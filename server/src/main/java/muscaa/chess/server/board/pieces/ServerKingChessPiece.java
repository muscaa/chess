package muscaa.chess.server.board.pieces;

import muscaa.chess.server.board.AbstractServerChessPiece;
import muscaa.chess.shared.board.ChessCell;
import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.shared.board.ChessMoves;
import muscaa.chess.shared.board.IChessPiece;
import muscaa.chess.shared.board.Validators;

public class ServerKingChessPiece extends AbstractServerChessPiece {
	
	public ServerKingChessPiece(ChessColor color) {
		super(1, color);
	}
	
	@Override
	public void findMoves(ChessMoves<AbstractServerChessPiece> moves, ChessCell cell) {
		//IValidator main = Validators.and(
				// TODO not near another king, not in check
		//);
		
		// horizontal
		moves.cell(
				cell.copy().add(ChessCell.ONE_ZERO),
				Validators.mainCell()
		);
		moves.cell(
				cell.copy().subtract(ChessCell.ONE_ZERO),
				Validators.mainCell()
		);
		
		// vertical
		moves.cell(
				cell.copy().add(ChessCell.ZERO_ONE),
				Validators.mainCell()
		);
		moves.cell(
				cell.copy().subtract(ChessCell.ZERO_ONE),
				Validators.mainCell()
		);
		
		// primary diagonal
		moves.cell(
				cell.copy().add(ChessCell.ONE_ONE),
				Validators.mainCell()
		);
		moves.cell(
				cell.copy().subtract(ChessCell.ONE_ONE),
				Validators.mainCell()
		);
		
		// secondary diagonal
		moves.cell(
				cell.copy().add(ChessCell.ONE_ZERO).subtract(ChessCell.ZERO_ONE),
				Validators.mainCell()
		);
		moves.cell(
				cell.copy().subtract(ChessCell.ONE_ZERO).add(ChessCell.ZERO_ONE),
				Validators.mainCell()
		);
	}
	
	@Override
	public IChessPiece copy() {
		ServerKingChessPiece copy = new ServerKingChessPiece(color);
		return copy;
	}
}
