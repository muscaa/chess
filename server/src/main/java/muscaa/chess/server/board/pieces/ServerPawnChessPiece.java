package muscaa.chess.server.board.pieces;

import muscaa.chess.server.board.AbstractServerChessPiece;
import muscaa.chess.shared.board.ChessCell;
import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.shared.board.ChessMoves;
import muscaa.chess.shared.board.ChessPieceMatrix;
import muscaa.chess.shared.board.IChessPiece;
import muscaa.chess.shared.board.Validators;

public class ServerPawnChessPiece extends AbstractServerChessPiece {
	
	public ServerPawnChessPiece(ChessColor color) {
		super(6, color);
	}
	
	@Override
	public ChessMoves getMoves(ChessPieceMatrix<AbstractServerChessPiece> matrix, ChessCell cell) {
		ChessMoves moves = new ChessMoves();
		
		moves.cell(Validators.OUT_OF_BOUNDS, cell.copy().add(color.getDirection()));
		
		return moves;
	}
	
	@Override
	public IChessPiece copy() {
		ServerPawnChessPiece copy = new ServerPawnChessPiece(color);
		return copy;
	}
}
