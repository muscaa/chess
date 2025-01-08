package muscaa.chess.board.piece.move.moves;

import muscaa.chess.board.Cell;
import muscaa.chess.board.matrix.Matrix;
import muscaa.chess.board.piece.AbstractPiece;
import muscaa.chess.board.piece.move.AbstractMove;
import muscaa.chess.board.piece.pieces.NullPiece;
import muscaa.chess.board.piece.pieces.RookPiece;
import muscaa.chess.utils.NamespacePath;

public class CastleMove extends AbstractMove {
	
	public CastleMove(NamespacePath id) {
		super(id);
	}
	
	@Override
	public void doMove(Matrix matrix, Cell from, Cell to) {
		int direction = from.x - to.x; // > 0 left, < 0 right
		
		AbstractPiece king = matrix.get(from);
		
		Cell left1 = from.subtract(new Cell(3, 0));
		if (direction > 0 && matrix.isInBounds(left1)) {
			matrix.set(from.subtract(new Cell(2, 0)), king);
			matrix.set(from, NullPiece.INSTANCE);
			
			AbstractPiece piece1 = matrix.get(left1);
			if (piece1 instanceof RookPiece rook1) {
				matrix.set(from.subtract(Cell.ONE_ZERO), rook1);
				matrix.set(left1, NullPiece.INSTANCE);
			} else if (piece1 == NullPiece.INSTANCE) {
				Cell left2 = left1.subtract(Cell.ONE_ZERO);
				if (matrix.isInBounds(left2)) {
					AbstractPiece piece2 = matrix.get(left2);
					if (piece2 instanceof RookPiece rook2) {
						matrix.set(from.subtract(Cell.ONE_ZERO), rook2);
						matrix.set(left2, NullPiece.INSTANCE);
					}
				}
			}
		}
		
		Cell right1 = from.add(new Cell(3, 0));
		if (direction < 0 && matrix.isInBounds(right1)) {
			matrix.set(from.add(new Cell(2, 0)), king);
			matrix.set(from, NullPiece.INSTANCE);
			
			AbstractPiece piece1 = matrix.get(right1);
			if (piece1 instanceof RookPiece rook1) {
				matrix.set(from.add(Cell.ONE_ZERO), rook1);
				matrix.set(right1, NullPiece.INSTANCE);
			} else if (piece1 == NullPiece.INSTANCE) {
				Cell right2 = right1.add(Cell.ONE_ZERO);
				if (matrix.isInBounds(right2)) {
					AbstractPiece piece2 = matrix.get(right2);
					if (piece2 instanceof RookPiece rook2) {
						matrix.set(from.add(Cell.ONE_ZERO), rook2);
						matrix.set(right2, NullPiece.INSTANCE);
					}
				}
			}
		}
	}
}
