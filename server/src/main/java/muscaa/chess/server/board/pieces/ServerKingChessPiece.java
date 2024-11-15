package muscaa.chess.server.board.pieces;

import java.util.HashSet;
import java.util.Set;

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
		//IValidator main = Validators.and(
				// TODO not near another king, not in check
		//);
		
		Set<ChessCell> possible = new HashSet<>();
		if (!moves.isEmulated()) {
			ChessPieceMatrix<AbstractServerChessPiece> matrix = moves.getMatrix();
			
			for (ChessCell ecell : matrix) {
				AbstractServerChessPiece epiece = matrix.get(ecell);
				if (epiece.getColor() == color) continue;
				
				ChessMoves<AbstractServerChessPiece> emoves = new ChessMoves<>(matrix, epiece, true);
				epiece.findMoves(emoves, ecell);
				
				emoves.getList()
						.stream()
						.filter(move -> move.getType() == ChessMoveType.TAKE)
						.map(ChessMove::getCell)
						.forEach(possible::add);
			}
		}
		
		// horizontal
		moves.cell(
				ChessMoveType.TAKE,
				cell.copy().add(ChessCell.ONE_ZERO),
				kingCell(possible)
		);
		moves.cell(
				ChessMoveType.TAKE,
				cell.copy().subtract(ChessCell.ONE_ZERO),
				kingCell(possible)
		);
		
		// vertical
		moves.cell(
				ChessMoveType.TAKE,
				cell.copy().add(ChessCell.ZERO_ONE),
				kingCell(possible)
		);
		moves.cell(
				ChessMoveType.TAKE,
				cell.copy().subtract(ChessCell.ZERO_ONE),
				kingCell(possible)
		);
		
		// primary diagonal
		moves.cell(
				ChessMoveType.TAKE,
				cell.copy().add(ChessCell.ONE_ONE),
				kingCell(possible)
		);
		moves.cell(
				ChessMoveType.TAKE,
				cell.copy().subtract(ChessCell.ONE_ONE),
				kingCell(possible)
		);
		
		// secondary diagonal
		moves.cell(
				ChessMoveType.TAKE,
				cell.copy().add(ChessCell.ONE_ZERO).subtract(ChessCell.ZERO_ONE),
				kingCell(possible)
		);
		moves.cell(
				ChessMoveType.TAKE,
				cell.copy().subtract(ChessCell.ONE_ZERO).add(ChessCell.ZERO_ONE),
				kingCell(possible)
		);
	}
	
	public static IValidator kingCell(Set<ChessCell> possible) {
		return Validators.and(
				Validators.mainCell(),
				Validators.avoid(possible)
		);
	}
}
