package muscaa.chess.shared.board;

import java.util.Iterator;

import fluff.vecmath.gen._int.vector.Vec2i;

public class ChessPieceMatrix<P extends IChessPiece> implements Iterable<Vec2i> {
	
	public static final int SIZE = 8;
	
	protected IChessPiece[][] pieces;
	// TODO add move history?
	
	public ChessPieceMatrix(P[][] pieces) {
		this.pieces = pieces;
	}
	
	public ChessPieceMatrix() {
		this.pieces = new IChessPiece[SIZE][SIZE];
	}
	
	public P get(Vec2i cell) {
		return (P) pieces[cell.y][cell.x];
	}
	
	public void set(Vec2i cell, P piece) {
		pieces[cell.y][cell.x] = piece;
	}
	
	@Override
	public Iterator<Vec2i> iterator() {
		return new CellIterator();
	}
	
	// TODO move method for history
	
	public ChessPieceMatrix<P> copy() {
		ChessPieceMatrix<P> copy = new ChessPieceMatrix<>();
		for (Vec2i cell : this) {
			copy.set(cell, (P) get(cell).copy());
		}
		return copy;
	}
	
	private class CellIterator implements Iterator<Vec2i> {
		
		public Vec2i cell = new Vec2i(-1, 0);
		
		@Override
		public boolean hasNext() {
			return cell.y + 1 < SIZE || cell.x + 1 < SIZE;
		}
		
		@Override
		public Vec2i next() {
			if (cell.x + 1 >= SIZE) {
				if (cell.y + 1 >= SIZE) {
					return null;
				} else {
					cell.y += 1;
				}
				
				cell.x = 0;
			} else {
				cell.x += 1;
			}
			
			return cell;
		}
	}
}
