package muscaa.chess.shared.board;

import java.util.Iterator;

public class ChessPieceMatrix<P extends IChessPiece> implements Iterable<ChessCell> {
	
	public static final int SIZE = 8;
	
	protected IChessPiece[][] pieces;
	// TODO add move history?
	
	public ChessPieceMatrix(P[][] pieces) {
		this.pieces = pieces;
	}
	
	public ChessPieceMatrix() {
		this.pieces = new IChessPiece[SIZE][SIZE];
	}
	
	public P get(ChessCell cell) {
		return (P) pieces[cell.y][cell.x];
	}
	
	public void set(ChessCell cell, P piece) {
		pieces[cell.y][cell.x] = piece;
	}
	
	@Override
	public Iterator<ChessCell> iterator() {
		return new CellIterator();
	}
	
	// TODO move method for history
	
	public ChessPieceMatrix<P> copy() {
		ChessPieceMatrix<P> copy = new ChessPieceMatrix<>();
		for (ChessCell cell : this) {
			copy.set(cell, (P) get(cell).copy());
		}
		return copy;
	}
	
	private class CellIterator implements Iterator<ChessCell> {
		
		public ChessCell cell = new ChessCell(-1, 0);
		
		@Override
		public boolean hasNext() {
			return cell.y + 1 < SIZE || cell.x + 1 < SIZE;
		}
		
		@Override
		public ChessCell next() {
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
