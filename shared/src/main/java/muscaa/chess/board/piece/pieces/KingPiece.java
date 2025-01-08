package muscaa.chess.board.piece.pieces;

import java.util.Map;

import muscaa.chess.board.Cell;
import muscaa.chess.board.Team;
import muscaa.chess.board.matrix.Matrix;
import muscaa.chess.board.piece.AbstractPiece;
import muscaa.chess.board.piece.PieceEntry;
import muscaa.chess.board.piece.PieceUtils;
import muscaa.chess.board.piece.move.AbstractMove;
import muscaa.chess.registry.registries.MoveRegistry;

public class KingPiece extends AbstractPiece {
	
	public KingPiece(PieceEntry<KingPiece> regEntry, Team team) {
		super(regEntry, team);
	}
	
	@Override
	public void findMoves(Map<Cell, AbstractMove> moves, Matrix matrix, Cell from) {
		Cell[] offsets = {
				// left
				new Cell(-1, -1),
				new Cell(-1, 0),
				new Cell(-1, 1),
				
				// middle
				new Cell(0, -1),
				new Cell(0, 1),
				
				// right
				new Cell(1, -1),
				new Cell(1, 0),
				new Cell(1, 1)
		};
		
		for (Cell off : offsets) {
			Cell cell = from.add(off);
			
			PieceUtils.basicCapture(this, moves, matrix, cell);
		}
		
		if (totalMoves != 0) return;
		
		boolean leftClearPath = true;
		for (int x = 1; x <= 2; x++) {
			Cell cell = from.subtract(new Cell(x, 0));
			
			if (!matrix.isInBounds(cell) || matrix.get(cell) != NullPiece.INSTANCE) {
				leftClearPath = false;
				break;
			}
		}
		
		Cell left = from.subtract(new Cell(2, 0));
		Cell left1 = from.subtract(new Cell(3, 0));
		if (leftClearPath && matrix.isInBounds(left1)) {
			AbstractPiece piece1 = matrix.get(left1);
			if (piece1 instanceof RookPiece rook1) {
				if (rook1.totalMoves == 0) {
					moves.put(left, MoveRegistry.CASTLE);
				}
			} else if (piece1 == NullPiece.INSTANCE) {
				Cell left2 = left1.subtract(Cell.ONE_ZERO);
				if (matrix.isInBounds(left2)) {
					AbstractPiece piece2 = matrix.get(left2);
					if (piece2 instanceof RookPiece rook2) {
						if (rook2.totalMoves == 0) {
							moves.put(left, MoveRegistry.CASTLE);
						}
					}
				}
			}
		}
		
		boolean rightClearPath = true;
		for (int x = 1; x <= 2; x++) {
			Cell cell = from.add(new Cell(x, 0));
			
			if (!matrix.isInBounds(cell) || matrix.get(cell) != NullPiece.INSTANCE) {
				rightClearPath = false;
				break;
			}
		}
		
		Cell right = from.add(new Cell(2, 0));
		Cell right1 = from.add(new Cell(3, 0));
		if (rightClearPath && matrix.isInBounds(right1)) {
			AbstractPiece piece1 = matrix.get(right1);
			if (piece1 instanceof RookPiece rook1) {
				if (rook1.totalMoves == 0) {
					moves.put(right, MoveRegistry.CASTLE);
				}
			} else if (piece1 == NullPiece.INSTANCE) {
				Cell right2 = right1.add(Cell.ONE_ZERO);
				if (matrix.isInBounds(right2)) {
					AbstractPiece piece2 = matrix.get(right2);
					if (piece2 instanceof RookPiece rook2) {
						if (rook2.totalMoves == 0) {
							moves.put(right, MoveRegistry.CASTLE);
						}
					}
				}
			}
		}
	}
	
	@Override
	public boolean isCheckable() {
		return true;
	}
}
