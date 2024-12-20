package muscaa.chess.board.matrix;

import java.util.Iterator;

import muscaa.chess.board.Cell;
import muscaa.chess.board.piece.IPiece;

public abstract class AbstractMatrix<P extends IPiece> implements Iterable<Cell> {
	
	protected int width;
	protected int height;
	protected IPiece[][] pieces;
	
	public AbstractMatrix(int width, int height) {
		reset(width, height);
	}
	
	public void reset(int width, int height) {
		this.width = width;
		this.height = height;
		this.pieces = new IPiece[height][width];
	}
	
	public P get(Cell cell) {
		IPiece[] row = pieces[cell.y];
		return (P) row[cell.x];
	}
	
	public void set(Cell cell, P piece) {
		pieces[cell.y][cell.x] = piece;
	}
	
	public boolean isInBounds(Cell cell) {
		return cell.x >= 0 && cell.x < width && cell.y >= 0 && cell.y < height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	@Override
	public Iterator<Cell> iterator() {
		return new CellIterator();
	}
	
	private class CellIterator implements Iterator<Cell> {
		
		public Cell cell = new Cell(-1, 0);
		
		@Override
		public boolean hasNext() {
			return cell.y + 1 < height || cell.x + 1 < width;
		}
		
		@Override
		public Cell next() {
			if (cell.x + 1 >= width) {
				if (cell.y + 1 >= height) {
					return null;
				} else {
					cell.y += 1;
				}
				
				cell.x = 0;
			} else {
				cell.x += 1;
			}
			
			return cell.copy();
		}
	}
}
