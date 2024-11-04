package muscaa.chess.server.board.pieces;

import muscaa.chess.server.board.AbstractServerChessPiece;
import muscaa.chess.shared.board.ChessCell;
import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.shared.board.ChessMoves;
import muscaa.chess.shared.board.ChessPieceMatrix;
import muscaa.chess.shared.board.IChessPiece;
import muscaa.chess.shared.board.Validators;

public class ServerKnightChessPiece extends AbstractServerChessPiece {
	
	public ServerKnightChessPiece(ChessColor color) {
		super(4, color);
	}
	
	@Override
	public ChessMoves getMoves(ChessPieceMatrix<AbstractServerChessPiece> matrix, ChessCell cell) {
		ChessMoves moves = new ChessMoves();
		
		moves.cell(Validators.OUT_OF_BOUNDS, cell.copy().add(new ChessCell(-1, -2)));
		moves.cell(Validators.OUT_OF_BOUNDS, cell.copy().add(new ChessCell(-2, -1)));
		
		moves.cell(Validators.OUT_OF_BOUNDS, cell.copy().add(new ChessCell(-1, 2)));
		moves.cell(Validators.OUT_OF_BOUNDS, cell.copy().add(new ChessCell(-2, 1)));
		
		moves.cell(Validators.OUT_OF_BOUNDS, cell.copy().add(new ChessCell(1, 2)));
		moves.cell(Validators.OUT_OF_BOUNDS, cell.copy().add(new ChessCell(2, 1)));
		
		moves.cell(Validators.OUT_OF_BOUNDS, cell.copy().add(new ChessCell(1, -2)));
		moves.cell(Validators.OUT_OF_BOUNDS, cell.copy().add(new ChessCell(2, -1)));
		
		return moves;
	}
	
	@Override
	public IChessPiece copy() {
		ServerKnightChessPiece copy = new ServerKnightChessPiece(color);
		return copy;
	}
}
